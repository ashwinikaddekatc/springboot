


//function CompareEndDate() {

//    var EndDate = $('#tax_group_end_date').val();
//    var StartDate = $('#tax_group_start_date').val(); ;



//    var inputToDate = Date.parse(EndDate);
//    var todayToDate = Date.parse(StartDate);

//    if (inputToDate < todayToDate) {
//        $('#tax_group_end_date').val('');
//        $('#tax_group_end_date').css('border-color', 'red');
//        $('#tax_group_end_date').css('background-color', '#FFE8A6');
//        $('#tax_group_end_date').attr('placeholder', 'Invalid Date');
//        $('#tax_group_end_date').focus();

//    }
//    else {

//        $('#tax_group_end_date').val(EndDate);

//    }


//}


function CompareNum(cnt) {
   
    
    var str = $('.saveapplicability' + cnt).val();

    if (isNaN(str)) {

        $('.saveapplicability' + cnt).val('');

        $('.saveapplicability' + cnt).val('');
        $('.saveapplicability' + cnt).attr('placeholder', 'Enter Digit.');
        $('.saveapplicability' + cnt).focus();
        $('.saveapplicability' + cnt).css('border-color', 'red');
        $('.saveapplicability' + cnt).css('background-color', '#FFE8A6');

    }
    else {

        if (str >= cnt | str == 0) {


            $('.saveapplicability' + cnt).val('');
            $('.saveapplicability' + cnt).attr('placeholder', 'Enter Correct  Digit.');
            $('.saveapplicability' + cnt).focus();
            $('.saveapplicability' + cnt).css('border-color', 'red');
            $('.saveapplicability' + cnt).css('background-color', '#FFE8A6');
        }
        else {
            $('.saveapplicability' + cnt).attr('placeholder', '');
            $('.saveapplicability' + cnt).css('border-color', '');
            $('.saveapplicability' + cnt).css('background-color', '');

            var sum = $('.saveapplicability' + cnt).val();
            CalculateTax(cnt, sum);
        }

    }
   
}

function CalculateTax(cnt, sum) {
    var app = $("#applicabilty").val();
    var a = parseFloat($("#groupTatal").val());
    var s = $("#LineTotal").val();
    if (s == "") {

    }
    else {
        a = a - parseFloat(s);
    }
    var LineTotal = $('.saveLineTotal' + sum).val();
    var Percentage = $('.savepercentage' + cnt).val();
    var Value = $('.savevalue' + cnt).val();
    if (Percentage == "" && Value == "") {
        $('.saveapplicability' + cnt).val('');
        $('.saveapplicability' + cnt).attr('placeholder', 'Select Tax Code First');
        $('.saveapplicability' + cnt).focus();
        $('.saveapplicability' + cnt).css('border-color', 'red');
        $('.saveapplicability' + cnt).css('background-color', '#FFE8A6');
    }
    else if (Percentage == "") {
        var Value = $('.savevalue' + cnt).val();
        $('.saveLineTotal' + cnt).val(Value);

        var x = parseFloat(Value) + parseFloat(a);
        $("#groupTatal").val(x);
    }
    else {

        var result = parseFloat(LineTotal) * parseFloat(Percentage) / 100;
        $('.saveLineTotal' + cnt).val(result);
        var finalResult = a + result;
        $("#groupTatal").val(finalResult);

    }

}
function getLastValofRow(count) {
    var a = $(".saveLineTotal" + count).val();
    var b = $(".saveapplicability" + count).val();
    $("#applicabilty").val(b);
    $("#LineTotal").val(a);

}

    function SelectTaxCode() {

        $(".savevalue" + 1).val(100);
        $(".saveLineTotal" + 1).val(100);
        $("#groupTatal").val(100);
        ColorOnEmpty('tax_group_type');
        var x = $("#tax_group_type").val();
        if (x != null) {
            var cnt = $("#RowCount").val();

            cnt = cnt - 1;
            //   alert(cnt);
            var listItem = "<option>" + "--Select--" + "</option>";
            $.getJSON('/TaxMaster/GetTaxCode/', { orgID: x }, function (data) {
                $.each(data, function (i, txt) {
                    listItem += "<option Value=" + txt.Value + ">" + txt.Text + "</option>";

                });
                for (var i = 2; i <= cnt; i++) {
                    $(".savetax_type" + i).html(listItem);

                }
            });
        }

    }
    function GetPercentage(cnt) {



        var x = $(".savetax_type" + cnt).val();

        var TaxPercentage = "";
        var TaxValue = "";
        var TaxApplicability = "";
        $.getJSON('/TaxMaster/TaxPerValue/', { TaxCode: x }, function (data) {
            TaxPercentage = data.tax_percentage;
            TaxValue = data.tax_value;
            TaxApplicability = parseInt(data.taxApplicability);
            $(".savepercentage" + cnt).val(TaxPercentage);
            $(".savevalue" + cnt).val(TaxValue);
            $("#TaxApplicability").val(TaxApplicability);

        });




    }


      function IsDataValid() {

        var flag = 0;
        var array = new Array('tax_group_name', 'tax_group_type');
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

    function getValues(cnt) {


        var isChecked = ($('[name="save_adv_immediate"]:checked').length);

        if (isChecked >= 1) {

            $(".rowNum1" + cnt).val(100);
            $(".rowNum2" + cnt).val(0);
        }
        else {
            $(".rowNum1" + cnt).val("");
            $(".rowNum2" + cnt).val("");
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