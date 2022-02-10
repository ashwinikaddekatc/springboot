var lastRow = "";
var nowRow = $("#rowCount").val();
function SelectedRow(rowNo) {

       
    nowRow = rowNo;

    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + nowRow).css('background-color', '#BCC6D7');

    getOtherDetails(raw);
      
}

function GetOriginalValue(count) {
    
    var getReceivedQty = parseInt($(".updateLineQTY" + count).val());

    var getPreVal = parseInt($("#updateLineQTYOK").val(getReceivedQty));

}
function GetCorrectValue(id, rowCount) {
    var getReceivedQty = parseInt($(".updateLineQTY" + rowCount).val());
    var prevValue = $("#updateLineQTYOK").val();
   
    if (isNaN(getReceivedQty)) {

        $('.updateLineQTY' + rowCount).val('');
        $('.updateLineQTY' + rowCount).attr('placeholder', 'Enter Valid Number');
        $('.updateLineQTY' + rowCount).focus();


        $('.updateLineQTY' + rowCount).css('border-color', 'red');
        $('.updateLineQTY' + rowCount).css('background-color', '#FFE8A6');
    }
    else {
        if (getReceivedQty > prevValue) {
         
            $('.updateLineQTY' + rowCount).val('');
            $('.updateLineQTY' + rowCount).attr('placeholder', 'Enter Valid Number');
            $('.updateLineQTY' + rowCount).focus();
            $('.updateLineQTY' + rowCount).css('border-color', 'red');
            $('.updateLineQTY' + rowCount).css('background-color', '#FFE8A6');
          
        }
        else {
            $('.updateLineQTY' + rowCount).css('border-color', '');
            $('.updateLineQTY' + rowCount).css('background-color', '');
            $('.updateLineQTY' + rowCount).attr('placeholder', '');
        }
//        $('.updateLineQTY' + rowCount).css('border-color', '');
//        $('.updateLineQTY' + rowCount).css('background-color', '');
//        $('.updateLineQTY' + rowCount).attr('placeholder', '');


    }
   

  
}



    function IsDataValid() {
       
        var flag = 0;
        var array = new Array('invoice_no', 'grn_shipment', 'grn_release_date');
        flag = IsControlValid(array);

        return flag;
    }
    function IsControlValid(array) {
        var ctrlArray = new Array();
        ctrlArray = array;

        var i = 0, j = 1;

        for (i = 0; i < ctrlArray.length; i++) {

            var fname = $('#' + ctrlArray[i]).val();

            if (fname == "") {

                $('#1' + ctrlArray[i]).css('color', 'red');


                j = 0;

            } else {

                $('#1' + ctrlArray[i]).css('color', 'black');
            }

        }
        return j;
    }

 



 

 
    function SetLocation() {
        var operaUnit = $('#ou_id');
        var menuId = operaUnit.find('option:selected').text();
        if (menuId != "") {
            $('#location').val(menuId);
        }
    }

    function ValidateSearchDetails() {
        var receiving_type = $("#grn_type").val();
        if (receiving_type == "397") {
         
            $("#sales_order_no").val("");
            $("#purchase_order_number").removeAttr('disabled', 'disabled').removeAttr('readonly', 'readonly');
           
            $("#sales_order_no").attr('disabled', 'disabled').attr('readonly', 'readonly');
            document.getElementById('sales_order_no').className = 'readonlyGreyColor';
            document.getElementById('purchase_order_number').className = 'readonly';
        }

        if (receiving_type == "398") {
          
            $("#purchase_order_number").val("");
            $("#sales_order_no").removeAttr('disabled', 'disabled').removeAttr('readonly', 'readonly');
            $("#purchase_order_number").attr('disabled', 'disabled').attr('readonly', 'readonly');

            document.getElementById('sales_order_no').className = 'readonly';
            document.getElementById('purchase_order_number').className = 'readonlyGreyColor';
        }
       

    }

    function ValidateSearchData() {

        if ($("#grn_type").val() != "") {
            var recType = $("#grn_type").val();
            if (recType == "1") {
                var po = $("#purchase_order_number").val();
                if (po == "") {

                    $('#1' + purchase_order_number).css('color', 'red');
                    $("#purchase_order_number").focus();
                    exit();
                }
                else {
                    var so = $("#sales_order_no").val();
                    if (so == "") {

                        $('#1' + sales_order_no).css('color', 'red');
                        $("#sales_order_no").focus();
                        exit();
                    }
                }
            }
        }
        else {
            $('#1' + grnreturntype).css('color', 'red');
            alert("Select the Return Type");
            $("#grn_type").focus();
            exit();
        }
      
    }
    