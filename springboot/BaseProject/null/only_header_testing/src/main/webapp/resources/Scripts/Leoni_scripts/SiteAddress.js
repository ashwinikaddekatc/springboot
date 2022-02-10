function SiteClearLines() {
    var GetCurrentRow = $("#RowForData").val();
    
    $(".saveName" + GetCurrentRow).val('');
   
    $(".saveAddType" + GetCurrentRow).val('');
    $(".saveAdd1" + GetCurrentRow).val('');
    $(".saveAdd2" + GetCurrentRow).val('');
    $(".saveStreet" + GetCurrentRow).val('');
    $(".saveCity" + GetCurrentRow).val('');

    $(".saveCounty" + GetCurrentRow).val('');
    $(".rowNum8" + GetCurrentRow).val('');
    $(".rowNumChar9" + GetCurrentRow).val('');
    $(".saveCustomerContact" + GetCurrentRow).val('');
    $(".saveEDILocation" + GetCurrentRow).val('');

    $(".saveW" + GetCurrentRow).val('');
    $(".saveOrderType" + GetCurrentRow).val('');
    $(".savePriceList" + GetCurrentRow).val('');  
    $(".rowNumChar15" + GetCurrentRow).val('');
    $(".savePostalCode" + GetCurrentRow).val('');
    
    $(".savePmFlg" + GetCurrentRow).val('').attr("Checked", false);
    $(".clsOwner" + GetCurrentRow).val('');

    //document.getElementById("address_id " + GetCurrentRow).value = '';
 
    var saveName = "";
   
    var saveAddType = "";
    var saveAdd1 = "";
    var saveAdd2 = ""
    var saveStreet = "";
    var saveCity = "";

    var saveCounty = "";
    var rowNum8 = "";
    var rowNumChar9 = "";
    var saveCustomerContact = "";
    var saveEDILocation = "";
    
    var saveW = "";
    var saveOrderType = "";
    var savePriceList = "";
    var rowNumChar15 = "";
    var savePostalCode = "";

    var savePmFlg = "";
    var clsOwner = "";

    //var address_id = "";
   
}

function SiteCopyAbove() {
    var GetCurrentRow = $("#RowForData").val();

    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var saveName = $(".saveName" + PreviousRow).val();
   
    var saveAddType = $(".saveAddType" + PreviousRow).val();
    var saveAdd1 = $(".saveAdd1" + PreviousRow).val();
    var saveAdd2 = $(".saveAdd2" + PreviousRow).val();
    var saveStreet = $(".saveStreet" + PreviousRow).val();
    var saveCity = $(".saveCity" + PreviousRow).val();

    var saveCounty = $(".saveCounty" + PreviousRow).val();
    var rowNum8 = $(".rowNum8" + PreviousRow).val();
    var rowNumChar9 = $(".rowNumChar9" + PreviousRow).val();
    var saveCustomerContact = $(".saveCustomerContact" + PreviousRow).val();
    var saveEDILocation = $(".saveEDILocation" + PreviousRow).val();

    var saveW = $(".saveW" + PreviousRow).val();
    var saveOrderType = $(".saveOrderType" + PreviousRow).val();
    var savePriceList = $(".savePriceList" + PreviousRow).val();
    var rowNumChar15 = $(".rowNumChar15" + PreviousRow).val();
    var savePostalCode = $(".savePostalCode" + PreviousRow).val();

    var savePmFlg = $(".savePmFlg" + PreviousRow).val();
    var clsOwner = $(".clsOwner" + PreviousRow).val();

    //var address_id = document.getElementById('address_id' + PreviousRow).value;
    
    $(".saveName" + GetCurrentRow).val(saveName);
   
    $(".saveAddType" + GetCurrentRow).val(saveAddType);
    $(".saveAdd1" + GetCurrentRow).val(saveAdd1);
    $(".saveAdd2" + GetCurrentRow).val(saveAdd2);
    $(".saveStreet" + GetCurrentRow).val(saveStreet);
    $(".saveCity" + GetCurrentRow).val(saveCity);

    $(".saveCounty" + GetCurrentRow).val(saveCounty);
    $(".rowNum8" + GetCurrentRow).val(rowNum8);
    $(".rowNumChar9" + GetCurrentRow).val(rowNumChar9);
    $(".saveCustomerContact" + GetCurrentRow).val(saveCustomerContact);
    $(".saveEDILocation" + GetCurrentRow).val(saveEDILocation);

    $(".saveW" + GetCurrentRow).val(saveW);
    $(".saveOrderType" + GetCurrentRow).val(saveOrderType);
    $(".savePriceList" + GetCurrentRow).val(savePriceList);
    $(".rowNumChar15" + GetCurrentRow).val(rowNumChar15);
    $(".savePostalCode" + GetCurrentRow).val(savePostalCode);

    $(".savePmFlg" + GetCurrentRow).val(savePmFlg);
    $(".clsOwner" + GetCurrentRow).val(clsOwner);

    //document.getElementById("address_id " + GetCurrentRow).value = address_id;

}

function SiteSelectedRowData(rowNo) {

    $("#RowForData").val(rowNo);
    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + rowNo).css('background-color', '#BCC6D7');


}