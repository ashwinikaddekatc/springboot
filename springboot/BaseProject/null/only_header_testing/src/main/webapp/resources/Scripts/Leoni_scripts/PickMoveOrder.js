
/////////////////////////////// Validation on grid no  /////////////////////////////////////

function checkGridNumPMO(rowCount) {

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
/////////////////////////////// ColorOnEmpty  /////////////////////////////////////

function ColorOnEmpty(ctrlName) {
    var value = $('#' + ctrlName).val().trim();

    if (value == "") {
        $('#1' + ctrlName).css('color', 'red');
    } else {
        $('#1' + ctrlName).css('color', 'black');
    }
}

//data validation

function IsDataValid() {

    var flag = 0;
    var array = new Array('transaction_type');

    flag = IsControlValid(array);

    return flag;
}

/////////////////////////////// IsControlValid  /////////////////////////////////////

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

/////////////////////////////// document ready /////////////////////////////////////





///////////////////////////////// Show Data /////////////////////////////////////

//function ShowMyData() {

//    var Reference = $("#reference").val();

//    var Source = $("#source").val();
//    $.ajax(
//        {
//            type: "POST",
//            url: '@Url.Action("ShowData", "PickMove")',
//            data: "&Reference=" + Reference + "&Source=" + Source,
//            success: function (htmlPartialView) {
//                $("#content").html(htmlPartialView);

//            },
//            error: function (xhr, textStatus, errorThrown) {

//            }
//        });

//    }

//    /////////////////////////////// Subbinventory /////////////////////////////////////
//    function GetSubInventory(id) {

//        if (id == "transaction_from_org") {

//            var a = $("#transaction_from_org").val();


//            var listItem = "<option>" + "--Select--" + "</option>";
//            $.getJSON('/InventoryTransaction/GetSubInventoryChange/', { orgID: a }, function (data) {
//                $.each(data, function (i, txt) {
//                    listItem += "<option Value=" + txt.Value + ">" + txt.Text + "</option>";

//                });

//                $("#transaction_from_subinventory").html(listItem);

//            });
//        }
//        if (id == "transaction_to_org") {

//            var a = $("#transaction_to_org").val();


//            var listItem = "<option>" + "--Select--" + "</option>";
//            $.getJSON('/InventoryTransaction/GetSubInventoryChange/', { orgID: a }, function (data) {
//                $.each(data, function (i, txt) {
//                    listItem += "<option Value=" + txt.Value + ">" + txt.Text + "</option>";

//                });

//                $("#transaction_to_sub_inventory").html(listItem);

//            });
//        }
//    }

//    /////////////////////////////// Locator /////////////////////////////////////



//    function GetFromLocator() {
//        var a = $("#transaction_from_subinventory").val();

//        $(".transaction_from_subinventory").val(a);
//        var listItem = "<option>" + "--Select--" + "</option>";
//        $.getJSON('/InventoryTransaction/GetFromLocator/', { subInventoryID: a }, function (data) {
//            $.each(data, function (i, txt) {
//                listItem += "<option Value=" + txt.Value + ">" + txt.Text + "</option>";

//            });

//            $("#transaction_locator").html(listItem);

//        });
//    }


//    /////////////////////////////// Item Count /////////////////////////////////////

//    function ItemCount(cnt) {
//        var a = $(".item_id" + cnt).val();


//        var listItem = "<option>" + "--Select--" + "</option>";
//        $.getJSON('/PickMove/GetAvailableQty/', { Item: a }, function (data) {
//            $.each(data, function (i, txt) {
//                listItem += "<option Value=" + txt.Value + ">" + txt.Text + "</option>";

//            });

//            $("#available_to_transact").html(listItem);

//        });

//    }



function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    document.getElementById("update_pick_move_order_id " + GetCurrentRow).value = '';

    $(".update_reference_2" + GetCurrentRow).val('');
    $(".item_id" + GetCurrentRow).val('');
    $(".update_item_name" + GetCurrentRow).val('');
    $(".rowNum3" + GetCurrentRow).val('');
    $(".update_Status" + GetCurrentRow).val('');
    document.getElementById("save_need_by_date " + GetCurrentRow).value = '';
    $(".save_atp_rule" + GetCurrentRow).val('');

    var update_pick_move_order_id = "";
    var update_reference_2 = "";
    var item_id = ""
    var update_item_name = "";
    var rowNum3 = ""
    var update_Status = "";
    var save_need_by_date = "";
    var save_atp_rule = "";
}


function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var Save_fin_date = document.getElementById('Save_fin_date ' + PreviousRow).value;
    var update_reference_2 = $(".update_reference_2" + PreviousRow).val();
    var item_id = $(".item_id" + PreviousRow).val();
    var update_item_name = $(".update_item_name" + PreviousRow).val();
    var rowNum3 = $(".rowNum3" + PreviousRow).val();
    var update_Status = $(".update_Status" + PreviousRow).val();
    var save_need_by_date = document.getElementById('save_need_by_date ' + PreviousRow).value;
    var save_atp_rule = $(".save_atp_rule" + PreviousRow).val();


    document.getElementById("update_pick_move_order_id " + GetCurrentRow).value = update_pick_move_order_id;
    $(".update_reference_2" + GetCurrentRow).val(update_reference_2);
    $(".item_id" + GetCurrentRow).val(item_id);
    $(".update_item_name" + GetCurrentRow).val(update_item_name);
    $(".rowNum3" + GetCurrentRow).val(rowNum3);
    $(".update_Status" + GetCurrentRow).val(update_Status);
    document.getElementById("save_need_by_date " + GetCurrentRow).value = save_need_by_date;
    $(".save_atp_rule" + GetCurrentRow).val(save_atp_rule);


}

function SelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}