function ClearLines() {
    var GetCurrentRow = $("#RowForData").val();

    $(".address_1" + GetCurrentRow).val('');
    $(".address_2" + GetCurrentRow).val('');
    $(".street_name" + GetCurrentRow).val('');   
    $(".city" + GetCurrentRow).val('');
    $(".county" + GetCurrentRow).val('');
    $(".postal_code" + GetCurrentRow).val('');
    $(".country" + GetCurrentRow).val('');
  

    var address_1 = "";
    var address_2 = ""
    var street_name = "";
    var city = ""
    var county = "";
    var postal_code = "";
    var country = ""

}


function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var address_1 = $(".address_1" + PreviousRow).val();
    var address_2 = $(".address_2" + PreviousRow).val();
    var street_name = $(".street_name" + PreviousRow).val();
    var city = $(".city" + PreviousRow).val();
    var county = $(".county" + PreviousRow).val();
    var postal_code = $(".postal_code" + PreviousRow).val();
    var country = $(".country" + PreviousRow).val();

    $(".address_1" + GetCurrentRow).val(address_1);
    $(".address_2" + GetCurrentRow).val(address_2);
    $(".street_name" + GetCurrentRow).val(street_name);
    $(".city" + GetCurrentRow).val(city);
    $(".county" + GetCurrentRow).val(county);
    $(".postal_code" + GetCurrentRow).val(postal_code);
    $(".country" + GetCurrentRow).val(country);

}

function SelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}