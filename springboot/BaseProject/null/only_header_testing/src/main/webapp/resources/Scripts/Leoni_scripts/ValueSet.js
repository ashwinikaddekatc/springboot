function IsDataValid() {

    var flag = 0;
    var array = new Array('name', 'value_set_end_date');
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
    $("#name").focus();
    $('#tbSave').click(function () {
        document.getElementById('Save').click();
    });
    $('#tbSearch').click(function () {
        document.getElementById('Search').click();
    });

    $('#tbNew').click(function () {
        document.getElementById('New').click();
    });
//    var isChecked = $("#dependent:checked").length;

//    if (isChecked == 1) {

//        $("#value_on").show();
//        $("#value").show();
//    }
//    else {
//        $("#value_on").hide();
//        $("#value").hide();
//    }
    var fl = $("#flag").val();

    setInterval(function () { $('#msgDiv1').hide("1000") }, 3000);
   
});
function GetChildValue() {
    var GetParentChild = $("#value_on").val();

    var listItem = "";
    $.getJSON('/LocatorFieldDefinition/ParentChildValue/', { ParentValue: GetParentChild }, function (data) {
        $.each(data, function (i, txt) {
            listItem += "<option Value=" + txt.Value + ">" + txt.Text + "</option>";




        });

        $("#value").html(listItem);

    });
}
function getValues() {

    var isChecked = $("#dependent:checked").length;

    if (isChecked == 1) {

        $("#value_on").show();
        $("#value").show();
    }
    else {
        $("#value_on").hide();
        $("#value").hide();
    }
}