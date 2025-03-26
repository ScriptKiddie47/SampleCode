# XSLT StyleSheet

## xsl:value-of

#### xmlns tags

input xml:

```xml
<?xml version="1.0" encoding="utf-8"?>
<soap12:Envelope xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
  <soap12:Body>
    <NumberToWords xmlns="http://www.dataaccess.com/webservicesserver/">
      <ubiNum>5</ubiNum>
    </NumberToWords>
  </soap12:Body>
</soap12:Envelope>
```

```xsl
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:soap12="http://www.w3.org/2003/05/soap-envelope"
xmlns:ns="http://www.dataaccess.com/webservicesserver/"
>
<xsl:template match="/">
	<v1>
		<xsl:value-of select="/soap12:Envelope/soap12:Body/ns:NumberToWords/ns:ubiNum"/> <!-- Here we are utilizing the whole xpath , so match="/" is not significant here-->
        <!-- <xsl:value-of select="soap12:Envelope/soap12:Body/ns:NumberToWords/ns:ubiNum"/> Here we are utilizing the fields from match="/" tag. Output is the same as below -->
	</v1>
</xsl:template>
</xsl:stylesheet>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<v1 xmlns:ns="http://www.dataaccess.com/webservicesserver/"
	xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">5</v1>
```

Example 2 

```xsl
<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:soap12="http://www.w3.org/2003/05/soap-envelope"
xmlns:ns="http://www.dataaccess.com/webservicesserver/"
>
<xsl:template match="/soap12:Envelope">
	<v2>
		<xsl:value-of select="soap12:Body/ns:NumberToWords/ns:ubiNum"/> <!-- Good Example of utilizing the match value and bulding upon it-->
	</v2>
</xsl:template>
</xsl:stylesheet>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<v2 xmlns:ns="http://www.dataaccess.com/webservicesserver/"
	xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">5</v2>
```

#### Without xmlns ( input value ) tags

It gets a little easier

```xml
<class>
	<student>
		<firstname>Graham</firstname>
		<lastname>Bell</lastname>
	</student>
</class>
```

```xml
<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
>

<xsl:template match="/">
	<firstNameOfStudent>
		<xsl:value-of select="/class/student/firstname"/>
	</firstNameOfStudent>
</xsl:template>

</xsl:stylesheet>
```
Output:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<firstNameOfStudent>Graham</firstNameOfStudent>
```

## xsl:copy-of

```xml
<!-- Input -->

<?xml version="1.0" encoding="utf-8"?>
<soap12:Envelope xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
  <soap12:Body>
    <NumberToWords xmlns="http://www.dataaccess.com/webservicesserver/">
      <ubiNum>5</ubiNum>
    </NumberToWords>
  </soap12:Body>
</soap12:Envelope>

<!-- XSLT -->

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:dpconfig="http://www.datapower.com/param/config"
	xmlns:dp="http://www.datapower.com/extensions">

	<xsl:template match="/">
		<copyWholeXMLData>
			<xsl:copy-of select="*" />
		</copyWholeXMLData>
	</xsl:template>

</xsl:stylesheet>

<!-- Output -->

<?xml version="1.0" encoding="UTF-8"?>
<copyWholeXMLData
	xmlns:dp="http://www.datapower.com/extensions"
	xmlns:dpconfig="http://www.datapower.com/param/config">
	<soap12:Envelope
		xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
		<soap12:Body>
			<NumberToWords
				xmlns="http://www.dataaccess.com/webservicesserver/">
				<ubiNum>5</ubiNum>
			</NumberToWords>
		</soap12:Body>
	</soap12:Envelope>
</copyWholeXMLData>
```
