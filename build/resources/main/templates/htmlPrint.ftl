<#ftl encoding="utf-8">
<#setting locale="en_US">
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset = "utf-8">
		<title>Facture</title>
	</head>
    <body>
		<h1>Invoice</h1>
		<ul><li><strong>Client : </strong>${facturation.invoice.customer}</li></ul>
		<table>
			<tr>
				<th scope="col"><strong>Play</strong></th>
				<th scope="col"><strong>Seats sold</strong></th>
				<th scope="col"><strong>Price</strong></th>
			</tr>
            <#list facturation.amounts as performance, price>
            <tr>
                <td>${performance.play.name}</td>
                <td>${performance.audience}</td>
                <td>${price?string.currency}</td>
            </tr>
            </#list>
			<tr>
				<th scope="col"><strong>Total owed:</strong></th>
				<td>${facturation.totalAmount?string.currency}</td>
			</tr>
			<tr>
				<th scope="col"><strong>Fidelity points earned:</strong></th>
				<td>${facturation.volumeCredits}</td>
			</tr>
		</table>
	</body>
</html>