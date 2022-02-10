function CheckDate(controlName) {
   
    var date = $('#' + controlName).val();

    var now = new Date();
    var s = date;
    var day = 0;
    var month = 0;
    var year = 0;
    if (s != "") {

        //split the date by hyphen
        s = date.split('-');
        //if date is not with hyphen then split by slash
        if (s.length != 3) {
            s = date.split('/');

        }
        //if date is not with hyphen then split by single space
        if (s.length != 3) {
            s = date.split(' ');

        }
        //check is day,year are number if not then alert message
        if (isNaN(s[0]) || isNaN(s[2]) || s[2].length != 4) {

            $('#' + controlName).val('');
            $('#' + controlName).css('border-color', 'red');
            $('#' + controlName).css('background-color', '#FFE8A6');
            $('#' + controlName).attr('placeholder', 'DD-MMM-YYYY');
            $('#' + controlName).focus();



        } else {
            if (s.length == 3) {
                day = s[0];
                month = s[1];
                year = s[2];
                $('#' + controlName).css('border-color', '');
                $('#' + controlName).css('background-color', '');
                //if month is in string then get month number

                if (isNaN(month)) {

                    month = parseInt(GetMonthNumber(month));

                } else {
                    month = parseInt(s[1]);
                }

                if (day > 31) {
                    $('#' + controlName).val('');
                    $('#' + controlName).attr('placeholder', 'Invalid Day');
                    $('#' + controlName).css('border-color', 'red');
                    $('#' + controlName).css('background-color', '#FFE8A6');
                } else if (month > 12) {
                    $('#' + controlName).val('');
                    $('#' + controlName).attr('placeholder', 'Invalid Month');
                    $('#' + controlName).focus();
                    $('#' + controlName).css('border-color', 'red');
                    $('#' + controlName).css('background-color', '#FFE8A6');
                } else {

                    //if all format is valid then show date in DD-MMM-YYYY

                    now.setDate(day);
                    now.setMonth(parseInt(month));
                    now.setYear(year);
                    var monthArr = new Array();
                    monthArr[0] = "";
                    monthArr[1] = "Jan";
                    monthArr[2] = "Feb";
                    monthArr[3] = "Mar";
                    monthArr[4] = "Apr";
                    monthArr[5] = "May";
                    monthArr[6] = "Jun";
                    monthArr[7] = "Jul";
                    monthArr[8] = "Aug";
                    monthArr[9] = "Sep";
                    monthArr[10] = "Oct";
                    monthArr[11] = "Nov";
                    monthArr[12] = "Dec";
                    var setDate = now.getDate() + "-" + monthArr[month] + "-" + now.getFullYear();
                    $('#' + controlName).val(setDate);



                    $('#' + controlName).css('border-color', '');
                    $('#' + controlName).css('background-color', '');


                }

            } else {

                $('#' + controlName).val('');
                $('#' + controlName).attr('placeholder', 'DD-MMM-YYYY');
                $('#' + controlName).focus();
                $('#' + controlName).css('border-color', 'red');
                $('#' + controlName).css('background-color', '#FFE8A6');
            }
        }
    }
}

function ColorOnEmpty(ctrlName) {
    var value = $('#' + ctrlName).val().trim();

    if (value == "") {
        $('#1' + ctrlName).css('color', 'red');
    } else {
        $('#1' + ctrlName).css('color', 'black');
    }
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


var lastRow = "";
function SelectedRow(rowNo) {

    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');
    $("#RowForData").val(rowNo);
   
}

function SelectedSubInventory(row) {
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = row;
    $('#sr_' + row).css('background-color', '#BCC6D7');
    $("#RowForData").val(rowNo);

}
function CompareNum(controlId) {

    if (controlId != "") {
        var str = $('#' + controlId).val();

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

function checkGridDate(rowCount) {
    
    var date = $('.rowDate' + rowCount).val();
    
    var now = new Date();
    var s = date;
    var day = 0;
    var month = 0;
    var year = 0;
    if (s != "") {

        //split the date by hyphen
        s = date.split('-');
        //if date is not with hyphen then split by slash
        if (s.length != 3) {
            s = date.split('/');

        }
        //if date is not with hyphen then split by single space
        if (s.length != 3) {
            s = date.split(' ');

        }
        //check is day,year are number if not then alert message
        if (isNaN(s[0]) || isNaN(s[2]) || s[2].length > 4) {

            $('.rowDate' + rowCount).val('');
            $('.rowDate' + rowCount).attr('placeholder', 'DD-MMM-YYYY');
            $('.rowDate' + rowCount).focus();


            $('.rowDate' + rowCount).css('border-color', 'red');
            $('.rowDate' + rowCount).css('background-color', '#FFE8A6');
        } else {
            if (s.length == 3) {
                day = s[0];
                month = s[1];
                year = s[2];

                $('.rowDate' + rowCount).css('border-color', '');
                $('.rowDate' + rowCount).css('background-color', '');
                //if month is in string then get month number

                if (isNaN(month)) {

                    month = parseInt(getMonthNumber(month));

                } else {
                    month = parseInt(s[1]);
                }

                if (day > 31) {
                    $('.rowDate' + rowCount).attr('placeholder', 'Invalid Day');
                    $('.rowDate' + rowCount).val('');
                    $('.rowDate' + rowCount).css('border-color', 'red');
                    $('.rowDate' + rowCount).css('background-color', '#FFE8A6');

                } else if (month > 12) {

                    $('.rowDate' + rowCount).val('');
                    $('.rowDate' + rowCount).attr('placeholder', 'Invalid Month');
                    $('.rowDate' + rowCount).focus();
                    $('.rowDate' + rowCount).css('border-color', 'red');
                    $('.rowDate' + rowCount).css('background-color', '#FFE8A6');
                } else {

                    //if all format is valid then show date in DD-MMM-YYYY

                    now.setDate(day);
                    now.setMonth(parseInt(month));
                    now.setYear(year);
                    var monthArr = new Array();
                    monthArr[0] = "";
                    monthArr[1] = "Jan";
                    monthArr[2] = "Feb";
                    monthArr[3] = "Mar";
                    monthArr[4] = "Apr";
                    monthArr[5] = "May";
                    monthArr[6] = "Jun";
                    monthArr[7] = "Jul";
                    monthArr[8] = "Aug";
                    monthArr[9] = "Sep";
                    monthArr[10] = "Oct";
                    monthArr[11] = "Nov";
                    monthArr[12] = "Dec";
                    var setDate = now.getDate() + "-" + monthArr[month] + "-" + now.getFullYear();
                    $('.rowDate' + rowCount).val(setDate);

                    $('.rowDate' + rowCount).css('border-color', '');
                    $('.rowDate' + rowCount).css('background-color', '');


                }

            } else {

                $('.rowDate' + rowCount).val('');
                $('.rowDate' + rowCount).attr('placeholder', 'DD-MMM-YYYY');
                $('.rowDate' + rowCount).focus();
                $('.rowDate' + rowCount).css('border-color', 'red');
                $('.rowDate' + rowCount).css('background-color', '#FFE8A6');
            }
        }
    }
}

function GetMonthNumber(month) {

    if (month == "Jan" || month == "jan" || month == "January" || month == "january")
        return 1;
    else if (month == "Feb" || month == "feb" || month == "February" || month == "february")
        return 2;
    else if (month == "Mar" || month == "mar" || month == "March" || month == "march")
        return 3;
    else if (month == "Apr" || month == "apr" || month == "April" || month == "april")
        return 4;
    else if (month == "May" || month == "may")
        return 5;
    else if (month == "Jun" || month == "jun" || month == "June" || month == "june")
        return 6;
    else if (month == "Jul" || month == "jul" || month == "July" || month == "july")
        return 7;
    else if (month == "Aug" || month == "aug" || month == "August" || month == "august")
        return 8;
    else if (month == "Sep" || month == "sep" || month == "September" || month == "september")
        return 9;
    else if (month == "Oct" || month == "oct" || month == "October" || month == "october")
        return 10;
    else if (month == "Nov" || month == "nov" || month == "November" || month == "november")
        return 11;
    else if (month == "Dec" || month == "dec" || month == "December" || month == "december")
        return 12;
    else
        return 13;
}
function DefaultDate(classa) {

    var m = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    var date = new Date();
    var dd = date.getDate() + "-" + m[date.getMonth()] + "-" + date.getFullYear();
    if (classa == "[object HTMLInputElement]") {

        var className = $(classa).attr("class");
        var s = className.split(' ');
        $('.' + s[0]).val(dd);
    } else {
        $('#' + classa).val(dd);
    }
}

function validateEmail(ctrlName) {

    var emailField = $('#' + ctrlName).val();

    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

    if (!filter.test(emailField)) {
        $('#' + ctrlName).attr('placeholder', 'Invalid Email Address.');
        $('#' + ctrlName).css('border-color', 'red');
        $('#' + ctrlName).css('background-color', '#FFE8A6');

        $('#' + ctrlName).val('');
        $('#' + ctrlName).focus();

    } else {
        $('#' + ctrlName).css('border-color', '');
        $('#' + ctrlName).css('background-color', '');
    }

}

function isNum(num1) {

    if (typeof num1 == 'number') {
        document.write(num1 + " is a number <br/>");
    }
    else 
     {
        document.write(num1 + " is not a number <br/>");
    }
}


function checkGridNum(rowCount) {

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
        $('.rowNum' + rowCount).attr('placeholder', '');
        $('.rowNum' + rowCount).css('border-color', '');
        $('.rowNum' + rowCount).css('background-color', '');
    }
}
  