function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    // document.getElementById("Save_fin_date " + GetCurrentRow).value = '';

    $(".Saveopunit" + GetCurrentRow).val('');
    $(".Saveunit_name" + GetCurrentRow).val('');
    $(".SaveSupplier_name" + GetCurrentRow).val('');
    $(".SaveSupplier_no" + GetCurrentRow).val('');
    $(".Savesupp_site" + GetCurrentRow).val('');
    $(".Savepaydate" + GetCurrentRow).val('');
    $(".Saveamount" + GetCurrentRow).val('');
    $(".Savecurrency" + GetCurrentRow).val('');
    $(".SavePayMethod" + GetCurrentRow).val('');
    $(".Save_Remittance_Det" + GetCurrentRow).val('');
    $(".Savecurrency1" + GetCurrentRow).val('');
    $(".SavePayMethod" + GetCurrentRow).val('');
    $(".Save_Remittance_Det1" + GetCurrentRow).val('');
    $(".Save_Remittance_Det2" + GetCurrentRow).val('');


    var Saveopunit = "";
    var Saveunit_name = "";
    var SaveSupplier_name = ""
    var SaveSupplier_no = "";
    var Savesupp_site = ""
    var Savepaydate = "";
    var Saveamount = "";
    var Savecurrency = ""
    var SavePayMethod = "";
    var Save_Remittance_Det = "";
    var Savecurrency1 = ""
    var SavePayMethod = "";
    var Save_Remittance_Det1 = ""
    var Save_Remittance_Det2 = "";

}


function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;


    var Saveopunit = $(".Saveopunit" + PreviousRow).val();
    var Saveunit_name = $(".Saveunit_name" + PreviousRow).val();
    var SaveSupplier_name = $(".SaveSupplier_name" + PreviousRow).val();
    var SaveSupplier_no = $(".SaveSupplier_no" + PreviousRow).val();
    var Savesupp_site = $(".Savesupp_site" + PreviousRow).val();
    var Savepaydate = $(".Savepaydate" + PreviousRow).val();
    var Saveamount = $(".Saveamount" + PreviousRow).val();
    var Savecurrency = $(".Savecurrency" + PreviousRow).val();
    var SavePayMethod = $(".SavePayMethod" + PreviousRow).val();
    var Save_Remittance_Det = $(".Save_Remittance_Det" + PreviousRow).val();
    var Savecurrency1 = $(".Savecurrency1" + PreviousRow).val();
    var SavePayMethod = $(".SavePayMethod" + PreviousRow).val();
    var Save_Remittance_Det1 = $(".Save_Remittance_Det1" + PreviousRow).val();
    var Save_Remittance_Det2 = $(".Save_Remittance_Det2" + PreviousRow).val();

    $(".Saveopunit" + GetCurrentRow).val(Saveopunit);
    $(".Saveunit_name" + GetCurrentRow).val(Saveunit_name);
    $(".SaveSupplier_name" + GetCurrentRow).val(SaveSupplier_name);
    $(".SaveSupplier_no" + GetCurrentRow).val(SaveSupplier_no);
    $(".Savesupp_site" + GetCurrentRow).val(Savesupp_site);
    $(".Savepaydate" + GetCurrentRow).val(Savepaydate);
    $(".Saveamount" + GetCurrentRow).val(Saveamount);
    $(".Savecurrency" + GetCurrentRow).val(Savecurrency);
    $(".SavePayMethod" + GetCurrentRow).val(SavePayMethod);
    $(".Save_Remittance_Det" + GetCurrentRow).val(Save_Remittance_Det);
    $(".Savecurrency1" + GetCurrentRow).val(Savecurrency1);
    $(".SavePayMethod" + GetCurrentRow).val(SavePayMethod);
    $(".Save_Remittance_Det1" + GetCurrentRow).val(Save_Remittance_Det1);
    $(".Save_Remittance_Det2" + GetCurrentRow).val(Save_Remittance_Det2);

}

function SelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}