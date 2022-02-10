function ColorOnEmpty(ctrlName) {

    var value = $('#' + ctrlName).val().trim();

    if (value == "") {

        $('#1' + ctrlName).css('color', 'red');
    } else {

        $('#1' + ctrlName).css('color', 'black');
    }
}

function IsDataValid() {

    var flag = 0;
    var array = new Array('transaction_type');

    flag = IsControlValid(array);

    return flag;
    
}
function IsDataValid1() {

    var flag = 0;
    var array = new Array('transaction_type', 'transaction_from_subinventory', 'transaction_item', 'transaction_qty', 'transaction_uom', 'transaction_account', 'transaction_reason');

    flag = IsControlValid(array);

    return flag;

}
function IsDataValid2() {

    var flag = 0;
    var array = new Array('transaction_type', 'transaction_to_sub_inventory', 'transaction_item', 'transaction_qty', 'transaction_uom', 'transaction_account', 'transaction_reason');

    flag = IsControlValid(array);

    return flag;

}
function IsDataValid3() {

    var flag = 0;
    var array = new Array('transaction_type', 'transaction_from_subinventory', 'transaction_to_sub_inventory', 'transaction_item', 'transaction_qty', 'transaction_uom', 'transaction_account', 'transaction_reason');

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


function readOnlyToSubInv() {

    var x = $("#transaction_type").val();
  
    ColorOnEmpty('transaction_type');
    if (x == 376) {
      
        //        document.getElementById("transaction_to_sub_inventory").style.pointerEvents = "none";
        //        document.getElementById("transaction_to_sub_inventory").style.display = "none";
        $("#disable_sub").hide();
        $("#br_hide").show();
        $("#disabled_inventory").show();

        $("#br_middle_hide").hide();
        document.getElementById("transaction_from_subinventory").style = "border-left:2px solid #FF3C3C"

    }

    if (x == 377) {
       
        //        document.getElementById("transaction_from_subinventory").style.pointerEvents = "none";
        //        document.getElementById("transaction_from_subinventory").style.display = "none";
        $("#disabled_inventory").hide();

        $("#br_middle_hide").show();
        document.getElementById("transaction_to_sub_inventory").style = "border-left:2px solid #FF3C3C"
        $("#disable_sub").show();
        $("#br_hide").hide();
    }

    if (x == 378) {
        
        $("#disabled_inventory").show();
        $("#disable_sub").show();
        $("#br_hide").hide();
        $("#br_middle_hide").hide();
        document.getElementById("transaction_to_org").style.pointerEvents = "none";

        document.getElementById("transaction_from_org").style.pointerEvents = "none";
        document.getElementById("transaction_from_subinventory").style = "border-left:2px solid #FF3C3C"
        document.getElementById("transaction_to_sub_inventory").style = "border-left:2px solid #FF3C3C"
    }

    


}
function checkGridNumINV(rowCount) {

    var num = $('.rowNum' + rowCount).val();


    if (isNaN(num)) {

        $('.rowNum' + rowCount).val('');
        $('.rowNum' + rowCount).attr('placeholder', 'Enter Valid Number');
        $('.rowNum' + rowCount).focus();


        $('.rowNum' + rowCount).css('border-color', 'red');
        $('.rowNum' + rowCount).css('background-color', '#FFE8A6');
    }
    else {

        $('.rowNum' + rowCount).attr('placeholder', '');

        $('.rowNum' + rowCount).css('border-color', '');
        $('.rowNum' + rowCount).css('background-color', '');
    }
}


function CalculateTotal() {
    var UnitCost = $("#cost_per_unit").val();
    var getQty = $("#transaction_qty").val();
    if (isNaN(UnitCost)) {

        $('#cost_per_unit').val('');
        $('#cost_per_unit').attr('placeholder', 'Enter Valid Number');
        $('#cost_per_unit').focus();


        $('#cost_per_unit').css('border-color', 'red');
        $('#cost_per_unit').css('background-color', '#FFE8A6');
    }
    else {

        $('#cost_per_unit').attr('placeholder', '');

        $('#cost_per_unit').css('border-color', '');
        $('#cost_per_unit').css('background-color', '');
    }
    if (isNaN(getQty)) {

        $('#transaction_qty').val('');
        $('#transaction_qty').attr('placeholder', 'Enter Valid Number');
        $('#transaction_qty').focus();


        $('#transaction_qty').css('border-color', 'red');
        $('#transaction_qty').css('background-color', '#FFE8A6');
    }
    else {

        $('#transaction_qty').attr('placeholder', '');

        $('#transaction_qty').css('border-color', '');
        $('#transaction_qty').css('background-color', '');
    }
    if (getQty != "" && UnitCost!="") {
        var Total = parseFloat(getQty) * parseFloat(UnitCost);
        $("#total_cost").val(Total);
    }
   
}