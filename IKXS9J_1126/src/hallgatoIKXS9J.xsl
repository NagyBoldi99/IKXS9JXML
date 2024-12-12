<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>Hallgatói lista</title>
            </head>
            <body>
                <h1>Hallgatói Lista</h1>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Név</th>
                        <th>NEPTUN Kód</th>
                        <th>Szak</th>
                    </tr>
                    <xsl:for-each select="hallgatok/hallgato">
                        <tr>
                            <td><xsl:value-of select="@id"/></td>
                            <td><xsl:value-of select="nev"/></td>
                            <td><xsl:value-of select="neptunkod"/></td>
                            <td><xsl:value-of select="szak"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
