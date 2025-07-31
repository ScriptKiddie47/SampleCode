// Auto Insurance Rules Engine - HTTP Server for curl testing
// Requires Node.js with built-in http module

const http = require('http');
const url = require('url');

// Import the rules engine (assuming it's in the same file or you can copy the classes here)
class AutoInsuranceRulesEngine {
    constructor() {
        this.rules = [];
        this.ruleCounter = 1; // For assigning rule numbers
        this.initializeRules();
    }

    initializeRules() {
        // Eligibility Rules
        this.addRule({
            name: "minimum_age_check",
            category: "eligibility",
            priority: 100,
            condition: (facts) => facts.applicant.age < 18,
            action: (facts, results) => {
                results.eligibility.approved = false;
                results.eligibility.issues.push("Applicant must be at least 18 years old");
            }
        });

        this.addRule({
            name: "license_experience_check",
            category: "eligibility", 
            priority: 90,
            condition: (facts) => facts.applicant.licenseYears < 1,
            action: (facts, results) => {
                results.eligibility.approved = false;
                results.eligibility.issues.push("Applicant must have at least 1 year of driving experience");
            }
        });

        this.addRule({
            name: "excessive_violations_check",
            category: "eligibility",
            priority: 80,
            condition: (facts) => facts.applicant.violations > 5,
            action: (facts, results) => {
                results.eligibility.approved = false;
                results.eligibility.issues.push("Too many traffic violations - maximum 5 allowed");
            }
        });

        // Premium Calculation Rules
        this.addRule({
            name: "base_premium_calculation",
            category: "premium",
            priority: 70,
            condition: (facts) => true,
            action: (facts, results) => {
                const vehicleTypeRates = {
                    sedan: 1200, suv: 1400, sports: 2500, truck: 1600, luxury: 3000, economy: 1000
                };
                results.premium.basePremium = vehicleTypeRates[facts.vehicle.type] || 1200;
                results.premium.adjustments.push({
                    name: "Base Premium", amount: results.premium.basePremium, type: "base"
                });
            }
        });

        this.addRule({
            name: "young_driver_surcharge",
            category: "premium",
            priority: 60,
            condition: (facts) => facts.applicant.age >= 18 && facts.applicant.age <= 25,
            action: (facts, results) => {
                const surcharge = results.premium.basePremium * 0.30;
                results.premium.adjustments.push({
                    name: "Young Driver Surcharge (30%)", amount: surcharge, type: "surcharge"
                });
            }
        });

        this.addRule({
            name: "senior_discount",
            category: "premium",
            priority: 60,
            condition: (facts) => facts.applicant.age >= 65,
            action: (facts, results) => {
                const discount = results.premium.basePremium * 0.15;
                results.premium.adjustments.push({
                    name: "Senior Discount (15%)", amount: -discount, type: "discount"
                });
            }
        });

        this.addRule({
            name: "accident_history_surcharge",
            category: "premium",
            priority: 50,
            condition: (facts) => facts.applicant.accidents > 0,
            action: (facts, results) => {
                const surchargePerAccident = results.premium.basePremium * 0.20;
                const totalSurcharge = surchargePerAccident * facts.applicant.accidents;
                results.premium.adjustments.push({
                    name: `Accident History Surcharge (${facts.applicant.accidents} accidents)`,
                    amount: totalSurcharge, type: "surcharge"
                });
            }
        });

        this.addRule({
            name: "no_claims_bonus",
            category: "premium",
            priority: 40,
            condition: (facts) => facts.applicant.accidents === 0 && facts.applicant.licenseYears >= 3,
            action: (facts, results) => {
                const bonus = Math.min(facts.applicant.licenseYears * 50, results.premium.basePremium * 0.25);
                results.premium.adjustments.push({
                    name: "No Claims Bonus", amount: -bonus, type: "bonus"
                });
            }
        });

        this.addRule({
            name: "location_adjustment",
            category: "premium",
            priority: 30,
            condition: (facts) => true,
            action: (facts, results) => {
                const locationMultipliers = { urban: 0.20, suburban: 0.05, rural: -0.10 };
                const multiplier = locationMultipliers[facts.applicant.location] || 0;
                const adjustment = results.premium.basePremium * multiplier;
                if (adjustment !== 0) {
                    results.premium.adjustments.push({
                        name: `Location Adjustment (${facts.applicant.location})`,
                        amount: adjustment, type: adjustment > 0 ? "surcharge" : "discount"
                    });
                }
            }
        });

        // Validation Rules
        this.addRule({
            name: "minimum_liability_coverage",
            category: "validation",
            priority: 20,
            condition: (facts) => facts.coverage.liability < 50000,
            action: (facts, results) => {
                results.validation.valid = false;
                results.validation.errors.push("Minimum liability coverage is $50,000");
            }
        });

        this.addRule({
            name: "vehicle_age_comprehensive_warning",
            category: "validation",
            priority: 15,
            condition: (facts) => facts.vehicle.age > 10 && facts.coverage.comprehensive,
            action: (facts, results) => {
                results.validation.warnings.push("Comprehensive coverage may not be cost-effective for vehicles over 10 years old");
            }
        });
    }

    addRule(rule) {
        rule.ruleNumber = this.ruleCounter++; // Assign a rule number
        this.rules.push(rule);
        this.rules.sort((a, b) => (b.priority || 0) - (a.priority || 0));
    }

    getRules() {
        const rulesByCategory = {};
        for (const rule of this.rules) {
            if (!rulesByCategory[rule.category]) {
                rulesByCategory[rule.category] = [];
            }
            rulesByCategory[rule.category].push({
                ruleNumber: rule.ruleNumber,
                name: rule.name,
                priority: rule.priority,
                category: rule.category
            });
        }
        return rulesByCategory;
    }

    execute(facts) {
        const results = {
            eligibility: { approved: true, issues: [], reasons: [] },
            premium: { basePremium: 0, adjustments: [], finalPremium: 0 },
            validation: { valid: true, warnings: [], errors: [] },
            claims: { eligible: true, indicators: [] },
            executionLog: [],
            ruleResults: [] // Add per-rule results for output
        };

        for (const rule of this.rules) {
            try {
                if (rule.condition(facts)) {
                    const logMsg = `[INFO] Executing rule #${rule.ruleNumber}: "${rule.name}"`;
                    console.log(logMsg);
                    results.executionLog.push(`✓ Rule #${rule.ruleNumber} "${rule.name}" executed`);
                    rule.action(facts, results);
                    results.ruleResults.push({
                        ruleNumber: rule.ruleNumber,
                        name: rule.name,
                        executed: true
                    });
                } else {
                    results.executionLog.push(`- Rule #${rule.ruleNumber} "${rule.name}" skipped`);
                    results.ruleResults.push({
                        ruleNumber: rule.ruleNumber,
                        name: rule.name,
                        executed: false
                    });
                }
            } catch (error) {
                const errorMsg = `[ERROR] Rule #${rule.ruleNumber} "${rule.name}" failed: ${error.message}`;
                console.error(errorMsg);
                results.executionLog.push(`✗ Error in rule #${rule.ruleNumber} "${rule.name}": ${error.message}`);
                results.ruleResults.push({
                    ruleNumber: rule.ruleNumber,
                    name: rule.name,
                    executed: false,
                    error: error.message
                });
            }
        }

        results.premium.finalPremium = results.premium.basePremium +
            results.premium.adjustments.reduce((sum, adj) => sum + adj.amount, 0);

        return results;
    }

    getRules() {
        const rulesByCategory = {};
        for (const rule of this.rules) {
            if (!rulesByCategory[rule.category]) {
                rulesByCategory[rule.category] = [];
            }
            rulesByCategory[rule.category].push({
                name: rule.name, priority: rule.priority, category: rule.category
            });
        }
        return rulesByCategory;
    }
}

// Sample policies for testing
const samplePolicies = {
    youngDriver: {
        applicant: { age: 22, licenseYears: 3, accidents: 0, violations: 1, location: "urban" },
        vehicle: { type: "sports", age: 1, value: 45000 },
        coverage: { liability: 250000, collision: true, comprehensive: true, deductible: 500 }
    },
    senior: {
        applicant: { age: 68, licenseYears: 45, accidents: 0, violations: 0, location: "rural" },
        vehicle: { type: "sedan", age: 3, value: 25000 },
        coverage: { liability: 500000, collision: true, comprehensive: true, deductible: 1000 }
    },
    highRisk: {
        applicant: { age: 35, licenseYears: 12, accidents: 3, violations: 2, location: "urban" },
        vehicle: { type: "suv", age: 8, value: 15000 },
        coverage: { liability: 100000, collision: false, comprehensive: false, deductible: 1000 }
    }
};

// HTTP Server
const engine = new AutoInsuranceRulesEngine();

const server = http.createServer((req, res) => {
    const parsedUrl = url.parse(req.url, true);
    const path = parsedUrl.pathname;
    const query = parsedUrl.query;

    // Set CORS headers
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS');
    res.setHeader('Access-Control-Allow-Headers', 'Content-Type');
    res.setHeader('Content-Type', 'application/json');

    if (req.method === 'OPTIONS') {
        res.writeHead(200);
        res.end();
        return;
    }

    // Log incoming request
    console.log(`[REQUEST] ${req.method} ${path}`);
    if (req.method === 'POST') {
        let body = '';
        req.on('data', chunk => { body += chunk.toString(); });
        req.on('end', () => {
            console.log(`[REQUEST BODY] ${body}`);
            // POST /process - Process custom policy data
            if (path === '/process') {
                try {
                    const policyData = JSON.parse(body);
                    const result = engine.execute(policyData);
                    res.writeHead(200);
                    res.end(JSON.stringify({
                        success: true,
                        data: result,
                        timestamp: new Date().toISOString()
                    }, null, 2));
                } catch (error) {
                    console.error(`[ERROR] Failed to process POST /process: ${error.message}`);
                    res.writeHead(400);
                    res.end(JSON.stringify({
                        success: false,
                        error: "Invalid JSON: " + error.message
                    }));
                }
                return;
            }
            // ...handle other POST endpoints if any...
        });
        return;
    }

    // GET /rules - Get all rules
    if (path === '/rules' && req.method === 'GET') {
        res.writeHead(200);
        res.end(JSON.stringify({
            success: true,
            data: engine.getRules(),
            timestamp: new Date().toISOString()
        }, null, 2));
        return;
    }

    // GET /samples - Get sample policies
    if (path === '/samples' && req.method === 'GET') {
        res.writeHead(200);
        res.end(JSON.stringify({
            success: true,
            data: samplePolicies,
            timestamp: new Date().toISOString()
        }, null, 2));
        return;
    }

    // GET /process/:sample - Process a sample policy
    if (path.startsWith('/process/') && req.method === 'GET') {
        const sampleName = path.split('/')[2];
        if (samplePolicies[sampleName]) {
            const result = engine.execute(samplePolicies[sampleName]);
            res.writeHead(200);
            res.end(JSON.stringify({
                success: true,
                sampleUsed: sampleName,
                data: result,
                timestamp: new Date().toISOString()
            }, null, 2));
        } else {
            res.writeHead(404);
            res.end(JSON.stringify({
                success: false,
                error: "Sample policy not found. Available: " + Object.keys(samplePolicies).join(', ')
            }));
        }
        return;
    }

    // GET / - API documentation
    if (path === '/' && req.method === 'GET') {
        res.writeHead(200);
        res.end(JSON.stringify({
            name: "Auto Insurance Rules Engine API",
            version: "1.0.0",
            endpoints: {
                "GET /": "This documentation",
                "GET /rules": "Get all available rules grouped by category",
                "GET /samples": "Get sample policy data for testing",
                "GET /process/:sample": "Process a sample policy (youngDriver, senior, highRisk)",
                "POST /process": "Process custom policy data (send JSON in request body)"
            },
            samplePolicyStructure: {
                applicant: { age: "number", licenseYears: "number", accidents: "number", violations: "number", location: "string" },
                vehicle: { type: "string", age: "number", value: "number" },
                coverage: { liability: "number", collision: "boolean", comprehensive: "boolean", deductible: "number" }
            },
            curlExamples: [
                "curl http://localhost:3000/",
                "curl http://localhost:3000/rules",
                "curl http://localhost:3000/samples", 
                "curl http://localhost:3000/process/youngDriver",
                "curl -X POST -H 'Content-Type: application/json' -d '{...policy data...}' http://localhost:3000/process"
            ]
        }, null, 2));
        return;
    }

    // 404 for unknown routes
    res.writeHead(404);
    res.end(JSON.stringify({
        success: false,
        error: "Endpoint not found. Try GET / for API documentation"
    }));
});

const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
    console.log(`🚗 Auto Insurance Rules Engine Server running on port ${PORT}`);
    console.log(`\n📖 API Documentation: http://localhost:${PORT}/`);
    console.log(`\n🧪 Quick test commands:`);
    console.log(`   curl http://localhost:${PORT}/`);
    console.log(`   curl http://localhost:${PORT}/rules`);
    console.log(`   curl http://localhost:${PORT}/process/youngDriver`);
    console.log(`   curl http://localhost:${PORT}/process/senior`);
    console.log(`   curl http://localhost:${PORT}/process/highRisk`);
});
