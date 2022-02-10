function PONumber() {

    var listItem = "";
    var poid=""
    $.getJSON('/PurchaseOrder/GetPONumber/', {}, function (data) {
        listItem = data.dept;
        poid = data.poid;
        $("#po_number").val(listItem);
        $("#pay_po_header_id1").val(poid);
        if (listItem != "") {
            $("#POLines").show();
        }

    });

}
function GetSupplierSiteData() {
 
    var s = $('#sup_name').val();

    var listItem = "<option>" + "" + "</option>";
    var SupAddRessID = "";
    var BillToAddress = "";
    var ShipToAddress = "";
    var supId = "";
    var FreightTerm = "";
    var PaymentTerm = "";
    var FOB = "";
    var carrier = "";
    var PaymentTermData="";
    if (s.trim() != "") {
       
        $.getJSON('/PurchaseOrder/GetSupplierSite/', { id: s }, function (data) {
            $.each(data.list, function (i, txt) {
                listItem += "<option Value=" + txt.Value + ">" + txt.Text + "</option>";
            });
            supId = data.supId;
          
            SupAddRessID = data.SupAddRessID;
           
            BillToAddress = data.BillToAddress;
           
            ShipToAddress = data.ShipToAddress;
           
            FreightTerm = data.FreightTerm;
            PaymentTerm = data.PaymentTerm;
            FOB = data.FOB;
            carrier = data.carrier;
            PaymentTermData = data.PaymentTermData;
            //  $('#sup_address_id').html(listItem);
            $('#sup_site').html(listItem);
            $('#sup_id').val(supId);
            $('#po_bill_to').val(BillToAddress);


            $('#po_ship_to').val(ShipToAddress);
            $('#sup_site').val(SupAddRessID);
            $('#savePaymentTerms').val(PaymentTerm);
            $('#saveFreightTerms').val(FreightTerm);
            $('#saveCarrier').val(carrier);
            $('#saveSupFob').val(FOB);
            $('#supplier_id').val(supId);
            $('#po_payment_term').val(PaymentTermData);
            $('#sup_siteDisplay').hide();
            $('#sup_site').show();
            $('#supSites').hide();
             
           
        });
        
    }

}
function sup_site_bill_ship_to() {
    var supplier_addressId = $('#sup_site').val();
    var SupplierId = $('#supplier_id').val();
 
    var Phone = "";
    var Email = "";
    var FreightTerm = "";
    var PaymentTerm = "";
    var FOB = "";
    var carrier = "";
    $.getJSON('/PurchaseOrder/FromSupplierBillShipAddress/', { supplier_addressID: supplier_addressId, SupplierId: SupplierId }, function (data) {
        Phone = data.phone;
        Email = data.email;
        FreightTerm = data.FreightTerm;
        PaymentTerm = data.PaymentTerm;
        FOB = data.FOB;
        carrier = data.carrier;
        $("#po_bill_to").val(Phone);


        $("#po_ship_to").val(Email);
        $("#savePaymentTerms").val(PaymentTerm);
        $("#saveFreightTerms").val(FreightTerm);
        $("#saveCarrier").val(carrier);
        $("#saveSupFob").val(FOB);
    });
}

function IsDataValid1() {

    var flag = 0;
    var array = new Array('po_type', 'sup_name', 'sup_site', 'po_buyer', 'po_currency', 'po_receiving_type', 'po_aggreement_start_date', 'po_aggreement_end_date', 'po_aggreed_total', 'po_order_type');

    flag = IsControlValid1(array);

    return flag;
}
function IsControlValid1(array) {

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

function IsDataValid() {
    var flag = 0;
    var array = new Array('po_type', 'sup_name', 'sup_site', 'po_buyer', 'po_currency', 'po_receiving_type','po_order_type');
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




function alert_box() {
    //'A'==Alert box
    //'C'==Confirm box
    // 'P'==Prompt box
    msg_box('This is alert box', 'A', function (return_val) {

    });
}

function confirm_box(cnt) {
    msg_box('Do Yo Want To Save Changes', 'C', function (return_val) {
        if (return_val == 'yes') {

            saverecord(cnt);
        }
        else {

        }
    });
}

function prompt_box() {
    msg_box('Enter your name', 'P', function (return_val) {
        alert("Your name is=" + return_val);
    });
}
function ValidateLine(cnt) {

    if ($(".saveLineType" + cnt).val() != "") {
        var item = $(".items" + cnt).val();
        var deliveryDate = $(".rowDate" + 10 + cnt).val();

        if (item == "") {
            alert("Enter Item in Line " + cnt);
            $(".items" + cnt).focus()

            exit();
        }

        if (deliveryDate == "") {

            alert("Enter Delivery Date in Line " + cnt);
            $(".rowDate" + 10 + cnt).focus()

            exit();
        }

    }
}



function GetLineTypeValue(cnt) {
    var LineType = $(".saveLineType" + cnt).val();
    if (LineType == 264) {
        $(".items" + cnt).show();
        $(".items2" + cnt).hide();

    }
    else if (LineType == 265) {
        $(".items" + cnt).hide();
        $(".items2" + cnt).show();

    }
    else if (LineType == 266) {
        $(".items" + cnt).show();
        $(".items2" + cnt).hide();

    }
}


function ClearLines() {
    //var fun1 = function () {

    var GetCurrentRow = $("#RowForData").val();

    $(".saveLineType" + GetCurrentRow).val('');

    $(".items" + GetCurrentRow).val('');


    $(".itemidToSave" + GetCurrentRow).val('');
    $(".descrptionToSave" + GetCurrentRow).val('');
    $(".uomToSave" + GetCurrentRow).val('');
    $(".quantityToSave" + GetCurrentRow).val('');
    $(".unitPriceToSave" + GetCurrentRow).val('');
    $(".totalPriceToSave" + GetCurrentRow).val('');
    $(".saveBuyer" + GetCurrentRow).val('');
    $(".saveStatus" + GetCurrentRow).val('');
    $(".savePONumber" + GetCurrentRow).val('');
    $(".save_requisition_receiving_type" + GetCurrentRow).val('');
    document.getElementById("saveNeedByDate " + GetCurrentRow).value = '';


    var save_requisition_receiving_type = "";
    var saveLineType = "";
    var items = ""
    var itemidToSave = "";
    var descrptionToSave = "";
    var uomToSave = "";
    var quantityToSave = "";
    var unitPriceToSave = "";
    var totalPriceToSave = "";
    var saveBuyer = "";
    var BASSGridDate = "";
    var saveStatus = "";
    var savePONumber = "";
    //}setTimeout(fun1, 1000);
    
}

function CopyAbove() {
    var GetCurrentRow = $("#RowForData").val();
    alert(GetCurrentRow);
    var PreviousRow = parseInt(GetCurrentRow) - 1;

    var saveReceivingType = $(".saveReceivingType" + PreviousRow).val();
    var saveLineType = $(".saveLineType" + PreviousRow).val();
    var items = $(".items" + PreviousRow).val();
    var itemidToSave = $(".itemidToSave" + PreviousRow).val();
    var descrptionToSave = $(".descrptionToSave" + PreviousRow).val();
    var uomToSave = $(".uomToSave" + PreviousRow).val();
    var quantityToSave = $(".quantityToSave" + PreviousRow).val();
    var unitPriceToSave = $(".unitPriceToSave" + PreviousRow).val();
    var totalPriceToSave = $(".totalPriceToSave" + PreviousRow).val();
//    var saveBuyer = $(".saveBuyer" + PreviousRow).val();
//    var BASSGridDate = document.getElementById('saveNeedByDate ' + PreviousRow).value;

        var updateStatus = $(".updateStatus" + PreviousRow).val();
//    var savePONumber = $(".savePONumber" + PreviousRow).val();

    alert("saveLineType " + saveLineType);
    alert("items " + items);
    alert("itemidToSave " + itemidToSave);
    alert("descrptionToSave " + descrptionToSave);
    alert("uomToSave " + uomToSave);
    alert("quantityToSave " + quantityToSave);
    alert("unitPriceToSave " + unitPriceToSave);
    alert("totalPriceToSave " + totalPriceToSave);
    alert("saveReceivingType " + saveReceivingType);
//    alert("BASSGridDate " + BASSGridDate);
    alert("updateStatus " + saveStatus);
//    alert("savePONumber " + savePONumber);

    $(".saveLineType" + GetCurrentRow).val(saveLineType);
    $(".saveReceivingType" + GetCurrentRow).val(saveReceivingType);
    $(".items" + GetCurrentRow).val(items);

    $(".itemidToSave" + GetCurrentRow).val(itemidToSave);
    $(".descrptionToSave" + GetCurrentRow).val(descrptionToSave);
    $(".uomToSave" + GetCurrentRow).val(uomToSave);
    $(".quantityToSave" + GetCurrentRow).val(quantityToSave);
    $(".unitPriceToSave" + GetCurrentRow).val(unitPriceToSave);
    $(".totalPriceToSave" + GetCurrentRow).val(totalPriceToSave);

//    $(".saveBuyer" + GetCurrentRow).val(saveBuyer);

//    // $("#saveNeedByDate " + GetCurrentRow).val(BASSGridDate);
//    document.getElementById("saveNeedByDate " + GetCurrentRow).value = BASSGridDate;
    $(".updateStatus" + GetCurrentRow).val(updateStatus);
//    $(".savePONumber" + GetCurrentRow).val(savePONumber);

}
