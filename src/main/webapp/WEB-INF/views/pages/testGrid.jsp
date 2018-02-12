<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
    <meta charset="utf-8" />
    <title>jqGrid Example</title>

	<!-- 
    <link rel="stylesheet" type="text/css" media="screen" href="static/jquery/css/jquery-ui.min.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="static/jquery/css/ui.jqgrid.min.css" />
	 -->
    
    <link href="static/jquery/css/jquery-ui.min.css" rel="stylesheet">
    <link href="static/jquery/css/ui.jqgrid-bootstrap.css" rel="stylesheet">
    
    <script type="text/javascript" src="static/jquery/js/jquery.min.js"></script> 
    <script type="text/javascript" src="static/jquery/js/i18n/grid.locale-kr.js"></script>
    <script type="text/javascript" src="static/jquery/js/jquery.jqGrid.min.js"></script>
    <script>
		$.jgrid.defaults.width = 780;
	</script>    
</head>

<body>
<div style="margin-left:20px">
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>
    <script type="text/javascript"> 
        $(document).ready(function () {
			
            $("#jqGrid").jqGrid({
                url: 'http://trirand.com/blog/phpjqgrid/examples/jsonp/getjsonp.php?callback=?&qwery=longorders',
                mtype: "GET",
				styleUI : 'Bootstrap',
                datatype: "jsonp",
                colModel: [
                    { label: 'OrderID', name: 'OrderID', key: true, width: 75 },
                    { label: 'Customer ID', name: 'CustomerID', width: 150 },
                    { label: 'Order Date', name: 'OrderDate', width: 150 },
                    { label: 'Freight', name: 'Freight', width: 150 },
                    { label:'Ship Name', name: 'ShipName', width: 150 }
                ],
				viewrecords: true,
                height: 250,
                rowNum: 20,
                pager: "#jqGridPager"
            });
        });
 
   </script>
</body>
</html>