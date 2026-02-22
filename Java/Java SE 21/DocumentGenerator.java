public class DocumentGenerator {
    public static void main(String[] args) {
        System.out.println("Welcome to Document Generator");
    }

    void generate(String documentType) {
        ProductAPI productAPI = new ProductAPI();
        BillingAPI billingAPI = new BillingAPI();
        AuditAPI auditAPI = new AuditAPI();
        if (documentType.equalsIgnoreCase("ProductBill")) {
            productAPI.getProjectData(documentType);
            billingAPI.getBillingData(documentType);
        }
        if (documentType.equalsIgnoreCase("ProductReport")) {
            productAPI.getProjectData(documentType);
            billingAPI.getBillingData(documentType);
            auditAPI.getAuditReport(documentType);
        }
    }
}

class ProductAPI {
    String getProjectData(String documentType) {
        System.out.println("Making an API Call to Fetch Product Data");
        return "Product Data is Product123 for" + documentType;
    }
}

class BillingAPI {
    String getBillingData(String documentType) {
        System.out.println("Making an API Call to Fetch Billing Data");
        return "Billing Data is Billing123 for " + documentType;
    }
}

class AuditAPI {
    String getAuditReport(String documentType) {
        if(documentType.equalsIgnoreCase("ProductReport")){
            System.out.println("Making an API Call to Fetch Audit Data from OnPrem Servers");
        }else{
            System.out.println("Making an API Call to Fetch Audit Data from Cloud Servers");
        }
        
        return "Audit Data is XYZ for " + documentType;
    }
}