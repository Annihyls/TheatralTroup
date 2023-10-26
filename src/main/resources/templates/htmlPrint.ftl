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
		<ul><li><strong>Client : </strong>${facturation.getCustomerName()}</li></ul>
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
            <#if facturation.getWasAvailableForAReduction() == true>
            <tr>
				<th scope="col"><strong>Amount you really paid:</strong></th>
				<td>${facturation.totalAmountAfterReduction?string.currency}</td>
			</tr>
			<tr>
            	<th scope="col"><strong> Your credit after the reduction:</strong></th>
            	<td>${facturation.getCustomerCredits()}</td>
            </tr>
            <#else>
            <tr>
                <th scope="col"><strong>Your fidelity points:</strong></th>
                <td>${facturation.getCustomerCredits()}</td>
            </tr>
            </#if>
		</table>
	</body>
</html>