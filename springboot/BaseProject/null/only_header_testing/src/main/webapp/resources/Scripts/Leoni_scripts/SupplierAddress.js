function SupplierAddClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    $(".rowNumChar90" + GetCurrentRow).val('');

    $(".saveAddType" + GetCurrentRow).val('');
    $(".saveAdd1" + GetCurrentRow).val('');
    $(".saveAdd2" + GetCurrentRow).val('');
    $(".saveStreet" + GetCurrentRow).val('');
    $(".rowNumChar57" + GetCurrentRow).val('');

    $(".rowNumChar58" + GetCurrentRow).val('');
    $(".rowNumChar" + GetCurrentRow).val('');
    $(".rowNumLines18" + GetCurrentRow).val('');
    $(".rowNumChar59" + GetCurrentRow).val('');
    $(".saveBillTo" + GetCurrentRow).val('');

    $(".saveShipTo" + GetCurrentRow).val('');
    $(".savePmFlg" + GetCurrentRow).val('').attr("Checked", false);
    $(".clsOwner" + GetCurrentRow).val('');
    
    document.getElementById("Addsupplier_id" + GetCurrentRow).value = '';

    var rowNumChar90 = "";

    var saveAddType = "";
    var saveAdd1 = "";
    var saveAdd2 = ""
    var saveStreet = "";
    var rowNumChar57 = "";

    var rowNumChar58 = "";
    var rowNumChar = "";
    var rowNumLines18 = "";
    var rowNumChar59 = "";
    var saveBillTo = "";

    var saveShipTo = "";
    var savePmFlg = "";
    var clsOwner = "";
   
    var Addsupplier_id = "";

}

function SupplierAddCopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var rowNumChar90 = $(".rowNumChar90" + PreviousRow).val();

    var saveAddType = $(".saveAddType" + PreviousRow).val();
    var saveAdd1 = $(".saveAdd1" + PreviousRow).val();
    var saveAdd2 = $(".saveAdd2" + PreviousRow).val();
    var saveStreet = $(".saveStreet" + PreviousRow).val();
    var rowNumChar57 = $(".rowNumChar57" + PreviousRow).val();

    var rowNumChar58 = $(".rowNumChar58" + PreviousRow).val();
    var rowNumChar = $(".rowNumChar" + PreviousRow).val();
    var rowNumLines18 = $(".rowNumLines18" + PreviousRow).val();
    var rowNumChar59 = $(".rowNumChar59" + PreviousRow).val();
    var saveBillTo = $(".saveBillTo" + PreviousRow).val();

    var saveShipTo = $(".saveShipTo" + PreviousRow).val();
    var savePmFlg = $(".savePmFlg" + PreviousRow).val();
    var clsOwner = $(".clsOwner" + PreviousRow).val();
   
    //var Addsupplier_id = document.getElementById('Addsupplier_id' + PreviousRow).value;

    $(".rowNumChar90" + GetCurrentRow).val(rowNumChar90);

    $(".saveAddType" + GetCurrentRow).val(saveAddType);
    $(".saveAdd1" + GetCurrentRow).val(saveAdd1);
    $(".saveAdd2" + GetCurrentRow).val(saveAdd2);
    $(".saveStreet" + GetCurrentRow).val(saveStreet);
    $(".rowNumChar57" + GetCurrentRow).val(rowNumChar57);

    $(".rowNumChar58" + GetCurrentRow).val(rowNumChar58);
    $(".rowNumChar" + GetCurrentRow).val(rowNumChar);
    $(".rowNumLines18" + GetCurrentRow).val(rowNumLines18);
    $(".rowNumChar59" + GetCurrentRow).val(rowNumChar59);
    $(".saveBillTo" + GetCurrentRow).val(saveBillTo);

    $(".saveShipTo" + GetCurrentRow).val(saveShipTo);
    $(".savePmFlg" + GetCurrentRow).val(savePmFlg);
    $(".clsOwner" + GetCurrentRow).val(clsOwner);
   
    //document.getElementById("Addsupplier_id" + GetCurrentRow).value = Addsupplier_id;

}

function SupplierAddSelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}

function selectSupplierAddressForSave(sup_add_id) {
    
    $("#selected_address_id").val(sup_add_id);
}