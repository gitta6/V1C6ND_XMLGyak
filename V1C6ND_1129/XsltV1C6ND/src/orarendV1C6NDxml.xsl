<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl = "http://www.w3.org/1999/XSL/Transform" version = "1.0" >
	<xsl:template match="/">
		<xsl:text>&#xa;</xsl:text>
		<html>
			<body>
				<h2>Potoczki Gitta órarend – 2023/24. I. félév</h2>
				<table border = "4">
					<tr bgcolor = "peachpuff">
						<th>Id</th>
						<th>Típus</th>
						<th>Kurzusnév</th>
						<th colspan="2">Időpont</th>
						<th>Helyszin</th>
						<th>Oktato</th>
						<th>Szak</th>
					</tr>
					<xsl:for-each select="V1C6ND_orarend/ora">
						<tr>
							<td><xsl:value-of select = "@id"/></td>
							<td><xsl:value-of select = "@tipus"/></td>
							<td><xsl:value-of select = "kurzusnev"/></td>
							<td><xsl:value-of select = "idopont/nap"/></td>
							<td><xsl:value-of select = "idopont/kezdes"/>h - <xsl:value-of select = "idopont/befejezes"/>h</td>
							<td><xsl:value-of select = "helyszin"/></td>
							<td><xsl:value-of select = "oktato"/></td>
							<td><xsl:value-of select = "szak"/></td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>	
	<xsl:output method="xml" encoding="utf-8" indent="yes"></xsl:output>
</xsl:stylesheet>