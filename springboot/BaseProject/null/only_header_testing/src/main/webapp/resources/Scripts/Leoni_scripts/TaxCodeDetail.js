

//function CompareEndDate() {

//    var EndDate = $('#end_date').val();

//    var StartDate = $('#start_date').val(); ;

//    var inputToDate = Date.parse(EndDate);

//    var todayToDate = Date.parse(StartDate);

//    if (inputToDate < todayToDate) {

//        $('#end_date').val('');
//        $('#end_date').css('border-color', 'red');
//        $('#end_date').css('background-color', '#FFE8A6');
//        $('#end_date').attr('placeholder', 'Invalid Date');
//        $('#end_date').focus();

//    }
//    else {

//        $('#end_date').val(EndDate);

//    }


//}

function ColorOnEmpty(ctrlName) {
    var value = $('#' + ctrlName).val().trim();

    if (value == "") {
        $('#1' + ctrlName).css('color', 'red');
    } else {
        $('#1' + ctrlName).css('color', 'black');
    }
    //
}

function IsDataValid() {

    var flag = 0;
    var array = new Array('tax_code', 'tax_name', 'tax_code_type', 'tax_code_applicability', 'section_type', 'section_code', 'form_name', 'start_date', 'rounding_factors');
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

//checkbox valiidatiion

function TaxValueGray(id) {
    CompareNum(id);
    var b = $("#tax_percentage").val();
    if (b != null) {
        document.getElementById("tax_value").style.pointerEvents = "none";
        document.getElementById("adhoc_amount").style.pointerEvents = "none";

    }
    if (b == "") {
        document.getElementById("tax_percentage").style.pointerEvents = "none";
        document.getElementById("tax_value").style = "border-left:2px solid #FF3C3C";
        document.getElementById("adhoc_amount").style = "border-left:2px solid #FF3C3C";
        var d = 0;
        $("#tax_value").val(d);

    }

}
function SetDataTaxValue() {

    var x = $("#tax_percentage").val();

    var y = $("#tax_value").val();
    if (x == "" && y == "") {

        document.getElementById("tax_value").style = "border-left:2px solid #FF3C3C";
        var b = 0;
        $("#tax_value").val(b);
    }
    else {

    }


}
function CheckValue() {
    var isChecked = $("#adhoc_amount:checked").length;
    var b = $("#tax_percentage").val();
    if (isChecked == 1) {
        if (b == "") {
            document.getElementById("tax_percentage").style.pointerEvents = "none";
            document.getElementById("tax_value").style = "border-left:2px solid #FF3C3C";
            document.getElementById("adhoc_amount").style = "border-left:2px solid #FF3C3C";
            var d = 0;
            $("#tax_value").val(d);


        }

    }
    if (isChecked == 0) {
        document.getElementById("tax_percentage").style = "border-left:2px solid #FF3C3C";
        document.getElementById("tax_value").style.pointerEvents = "none";
        document.getElementById("adhoc_amount").style.pointerEvents = "none";
    }

}

//document .ready


