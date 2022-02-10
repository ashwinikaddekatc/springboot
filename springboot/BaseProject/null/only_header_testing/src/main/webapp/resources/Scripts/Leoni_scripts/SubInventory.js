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
    var array = new Array('sub_invetory_name', 'sub_inventory_description', 'sub_inventory_locator_control', 'sub_invetory_location');
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




function Disable_Button() {
    var x = parseInt($("#sub_inventory_locator_control").val());

    if (x == 1) {
        $("#copy").show();
    }
    else if (x == 2) {
        $("#copy").hide();
    }
    ColorOnEmpty('sub_inventory_locator_control');
}

