function CompareEndDate() {

    var EndDate = $('#payment_terms_end_date').val();
    var StartDate = $('#payment_terms_start_date').val(); ;



    var inputToDate = Date.parse(EndDate);
    var todayToDate = Date.parse(StartDate);

    if (inputToDate < todayToDate) {
        $('#payment_terms_end_date').val('');
        $('#payment_terms_end_date').css('border-color', 'red');
        $('#payment_terms_end_date').css('background-color', '#FFE8A6');
        $('#payment_terms_end_date').attr('placeholder', 'Invalid Date');
        $('#payment_terms_end_date').focus();

    }
    else {

        $('#payment_terms_end_date').val(EndDate);

    }


}



function checkGridNumpm(rowCount) {

    var num = $('.rowNum' + rowCount).val();

    //check if valid number
    if (isNaN(num)) {

        $('.rowNum' + rowCount).val('');
        $('.rowNum' + rowCount).attr('placeholder', 'Enter Valid Number');
        $('.rowNum' + rowCount).focus();


        $('.rowNum' + rowCount).css('border-color', 'red');
        $('.rowNum' + rowCount).css('background-color', '#FFE8A6');
    }
    else {
        $('.rowNum' + rowCount).css('border-color', '');
        $('.rowNum' + rowCount).css('background-color', '');
        $('.rowNum' + rowCount).attr('placeholder', '');


    }
}

function CompareNum(controlId) {

    if (controlId != "") {
        var str = $('#' + controlId).val();
        rangevalid();
        if (isNaN(str)) {

            $('#' + controlId).val('');
            $('#' + controlId).css('border-color', 'red');
            $('#' + controlId).css('background-color', '#FFE8A6');
            $('#' + controlId).attr('placeholder', 'Use Only Number.');
            $('#' + controlId).focus();
        }
        else {
            $('#' + controlId).css('border-color', '');
            $('#' + controlId).css('background-color', '');
        }
    }
}

function rangevalid() {

    var cutofdays = $('#cut_of_days').val();

    var h = 31;
    var l = 0;

    if (isNaN(cutofdays)) {
        $('#cut_of_days').val('');
        $('#cut_of_days').css('border-color', 'red');
        $('#cut_of_days').css('background-color', '#FFE8A6');
        $('#cut_of_days').attr('placeholder', 'Please enter digit');
        $('#cut_of_days').focus();
    }

    else if (cutofdays > h || cutofdays < l) {
        $('#cut_of_days').val('');
        $('#cut_of_days').css('border-color', 'red');
        $('#cut_of_days').css('background-color', '#FFE8A6');
        $('#cut_of_days').attr('placeholder', 'days should >31');
        $('#cut_of_days').focus();
    }
    else {
        $('#cut_of_days').css('border-color', '');
        $('#cut_of_days').css('background-color', '');
        $('#cut_of_days').attr('placeholder', '');
    }

}


function IsDataValid() {

    var flag = 0;
    var array = new Array('name', 'cut_of_days', 'payment_terms_start_date');
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


$(document).ready(function () {
    $("#New").hide();
    $('#empNameToFind').val('');


    $('#tbSave').click(function () {
        document.getElementById('Save').click();
    });
    $('#tbSearch').click(function () {
        document.getElementById('Search').click();
    });
    $('#tbNew').click(function () {
        document.getElementById('New').click();
    });
    $('#tbDelete').click(function () {
        document.getElementById('Delete').click();
    });
    $('#searchMsg').hide();
    setInterval(function () { $('#msgDiv').hide("1000") }, 3000);
});

function getValues(cnt) {
   

    var isChecked = ($('[name="save_adv_immediate"]:checked').length);

    if (isChecked >= 1) {

        $(".rowNum1" + cnt).val(100);
        $(".rowNum1" + cnt).attr('readonly', 'readonly');
        $(".rowNum2" + cnt).val(0);
        $(".rowNum2" + cnt).attr('readonly', 'readonly');
        var gdv = document.getElementById('installement1');
        var tbl = gdv.getElementsByTagName("tr");

        for (i = cnt + 1; i <= tbl.length; i++) {

            tbl[i].style.display = 'none';
        }

            }
    else {
        $(".rowNum1" + cnt).val("");
        $(".rowNum2" + cnt).val("");
    }
}
//function getCheckboxValues(id) {

//    alert("1");
//    var isChecked = ($('#discount_on_bill:checked').length);

//    if (isChecked >= 1) {
//        $("#discount_bill_value1").show();
//    }
//    else {
//        $("#discount_bill_value1").hide();
//    }
//}
//function getCheckboxValues(id) {

//    alert("2");
//    var isChecked = ($('#discount_on_bill:checked').length);

//    if (isChecked >= 1) {
//        $("#discount_bill_value1").show();
//    }
//    else {
//        $("#discount_bill_value1").hide();
//    }
//}
function getCheckboxValues(id) {


    var isChecked = ($('#discount_on_bill:checked').length);

    if (isChecked >= 1) {
        $("#discount_bill_value1").show();

    }
    else {
        $("#discount_on_bill_percentage").val('');
        $("#discount_bill_value1").hide();
    }
}
function getCheckboxValues1(id) {


    var isChecked = ($('#discount_on_line_item:checked').length);
    
    if (isChecked >= 1) {

        $("#discount_on_line_item1").show();

    }
    else {
        $("#discount_on_line_item_percentage").val('');
        $("#discount_on_line_item1").hide();
         
    }
}
function getCheckboxValues2(id) {

    var isChecked = ($('#discount_on_invoice_amount:checked').length);

    if (isChecked >= 1) {

        $("#discount_on_invoice_amount1").show();

    }
    else {

        $("#discount_on_invoice_amount_percentage").val('');
        $("#discount_on_invoice_amount1").hide();
    }
}
function getCheckboxValues3(id) {


    var isChecked = ($('#installement:checked').length);

    if (isChecked >= 1) {
        $("#installement1").show();

    }
    else {
        $("#installement1").hide();
    }
}
function getValuesUpdate(cnt) {


    var isChecked = ($('[name="updateadv_immediate"]:checked').length);

    if (isChecked >= 1) {

        $(".rowNum1" + cnt).val(100);
        $(".rowNum2" + cnt).val(0);
    }
    else {
        $(".rowNum1" + cnt).val("");
        $(".rowNum2" + cnt).val("");
    }
}

