function SetGender() {
    var value = $('#Title').val();
    if (value == "79" || value == "81") {
        $('#Gender').val('0');
    } else if (value == "80") {
        $('#Gender').val('1');

    } else {
        $('#Gender').val('');
    }
}

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
            alert("Invalid Date");
            $('#' + controlName).val('');
            $('#' + controlName).attr('placeholder', 'DD-MMM-YYYY');
            $('#' + controlName).focus();
        } else {
            if (s.length == 3) {
                day = s[0];
                month = s[1];
                year = s[2];

                //if month is in string then get month number

                if (isNaN(month)) {

                    month = parseInt(GetMonthNumber(month));

                } else {
                    month = parseInt(s[1]);
                }

                if (day > 31) {
                    alert("Invalid Day");
                    $('#' + controlName).val('');
                } else if (month > 12) {
                    alert("Invalid Month");
                    $('#' + controlName).val('');
                    $('#' + controlName).attr('placeholder', 'DD-MMM-YYYY');
                    $('#' + controlName).focus();
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




                }

            } else {
                alert("Invalid Date");
                $('#' + controlName).val('');
                $('#' + controlName).attr('placeholder', 'DD-MMM-YYYY');
                $('#' + controlName).focus();
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

/*  Hide message after a while */
$(document).ready(function () {


    setInterval(function () { $('#msgDiv').hide("1000") }, 3000);
    clearInterval();

});

function ValidateSearch() {

    var custName = $('#empNameToFind').val();
    var userName = $('#userIdToFind').val();
    alert(custName);
    if (custName.trim() != "") {

        document.getElementById('tdEmpSearch').style.border = '1px Solid #CCCCCC';
        window.document.getElementById("btnSearch").type = "submit";
    }
    else if (userName.trim() != "") {

        document.getElementById('tdUserSearch').style.border = '1px Solid #CCCCCC';
        window.document.getElementById("btnSearch").type = "submit";
    }
    else {
        document.getElementById('tdEmpSearch').style.border = '1px Solid #FF7D7D';
        $('#empNameToFind').attr('placeholder', 'Enter term...');
        $('#empNameToFind').focus();
        document.getElementById('tdUserSearch').style.border = '1px Solid #FF7D7D';
        $('#userIdToFind').attr('placeholder', 'Enter term...');
        $('#userIdToFind').focus();
    }

}

$(function () {
    $('#Joining_Date').datepicker({
        dateFormat: "dd-M-yy",
        changeMonth: true,
        changeYear: true
    });
});

$(function () {
    $('#Passport_Exp_Date').datepicker({
        dateFormat: "dd-M-yy",
        changeMonth: true,
        changeYear: true
    });
});

$(function () {
    $('#emp_visa_valid_until').datepicker({
        dateFormat: "dd-M-yy",
        changeMonth: true,
        changeYear: true
    });
});

$(function () {
    $('#emp_start_date').datepicker({
        dateFormat: "dd-M-yy",
        changeMonth: true,
        changeYear: true
    });
});

$(function () {
    $('#Emp_end_date').datepicker({
        dateFormat: "dd-M-yy",
        changeMonth: true,
        changeYear: true
    });
});


function validatePassword() {

    var newPassword = document.getElementById('password').value;
    var minNumberofChars = 6;
    var maxNumberofChars = 16;
    var regularExpression = /^[a-zA-Z0-9!@#$%^&*]{6,16}$/;
//    var alertbox = $("#alert_box").show();
    if (newPassword.length < minNumberofChars)
    {
        //alert("Minimum length is 6.");
        

//        msg_box('Minimum length is 6.', 'A', function (return_val) {

//        });
        $.messager.alert('User Creation', 'Minimum length is 6.!');
        $('#password').val('');
        $('#confirm_password').val('');
        $('#password').focus();
}  if (newPassword.length > maxNumberofChars) {
        //alert("Maximum length is 15.");
       

//        msg_box('Maximum length is 15.', 'A', function (return_val) {

//        });
        $.messager.alert('User Creation', 'Maximum length is 15.');
        $('#password').val('');
        $('#confirm_password').val('');
        $('#password').focus();
} 
    if (!regularExpression.test(newPassword)) {
        //alert("password should contain atleast one number and one special character");
        //var alertbox = $("#alert_box").show();

//        msg_box('password should contain atleast one number and one special character.', 'A', function (return_val) {

        //        });
        // there is not needed at beggining
//        $.messager.alert('User Creation', 'password should contain atleast one number and one special character.');
        $('#password').val('');
        $('#confirm_password').val('');
        $('#password').focus();
    }
}

function alert_box() {
    var alertbox = $("#alert_box").show();

    msg_box('This is alert box', 'A', function (return_val) {

    });
}

function confirm_box(id) {
    var confrimbox = $("#confirm_box").show();

    msg_box('Do You Want To Save Changes', 'C', function (return_val) {
        if (return_val == 'yes') {

            SaveOperation(id);
        }
        else {

        }
    });
}

function prompt_box() {
    var prompt_box = $("#prompt_box").show();
    msg_box('Enter your name', 'P', function (return_val) {
        alert("Your name is=" + return_val);
    });
}