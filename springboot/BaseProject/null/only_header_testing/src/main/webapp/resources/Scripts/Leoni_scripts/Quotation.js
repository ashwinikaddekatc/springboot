function CompareEndDate() {

    var EndDate = $('#quotation_end_date').val();
    var StartDate = $('#quotation_start_date').val(); ;



    var inputToDate = Date.parse(EndDate);
    var todayToDate = Date.parse(StartDate);

    if (inputToDate < todayToDate) {
        $('#quotation_end_date').val('');
        $('#quotation_end_date').css('border-color', 'red');
        $('#quotation_end_date').css('background-color', '#FFE8A6');
        $('#quotation_end_date').attr('placeholder', 'Invalid Date');
        $('#quotation_end_date').focus();

    }
    else {

        $('#quotation_end_date').val(EndDate);

    }


}


function IsDataValid() {

    var flag = 0;
    var array = new Array('quotation_type', 'supplier');
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


function CheckNumber(rowCount) {
    var num = $('.discountToSave' + rowCount).val();

    //check if valid number
    if (isNaN(num)) {

        $('.discountToSave' + rowCount).val('');
        $('.discountToSave' + rowCount).attr('placeholder', 'Enter Valid Number');
        $('.discountToSave' + rowCount).focus();


        $('.discountToSave' + rowCount).css('border-color', 'red');
        $('.discountToSave' + rowCount).css('background-color', '#FFE8A6');
    }
    else {
        $('.discountToSave' + rowCount).css('border-color', '');
        $('.discountToSave' + rowCount).css('background-color', '');
        $('.discountToSave' + rowCount).attr('placeholder', '');

    }

}




function LineValidate() {
    var data = 0;
    var RowCount = $("#RowC").val();
    RowCount = RowCount - 1;

    var alert_box = $("#alert_box").show();
    for (var i = 1; i <= RowCount; i++) {
        if ($(".saveLineType" + i).val() != "") {
            var item = $(".items" + i).val();
            var needbydate = $(".rowDate" + 9 + i).val();
            var delivarydate = $(".rowDate" + 10 + i).val();
            if (item == "") {
                msg_box('Enter Item in Line' + i, 'A', function (return_val) { });
             
                $(".items" + i).focus()

                exit();
            }
            if (needbydate == "") {
               
                msg_box('Enter Need By Date in Line ' + i, 'A', function (return_val) { });
                $(".rowDate" + 9 + i).focus()

                exit();
            }
            if (needbydate == "") {
                msg_box('Enter Dilivery Date  in Line ' + i, 'A', function (return_val) { });
            
                $(".rowDate" + 10 + i).focus()

                exit();
            }
        }
        else {
            data = data + 1;


        }

    }


    return data;
}



 function GetLineTypeValue(rowNo) {

        var LineType = $(".saveLineType" + rowNo).val();

        var reqTotal = 1;
        var toalprice = $('.totalPriceToSave' + rowNo).val();
        var zero = '0';

        if (reqTotal > zero && toalprice != "") {

            var total = parseInt(reqTotal) - parseInt(toalprice);
        //    $("#rfq_total").val(total);

        }

       
        $('.items' + rowNo).val('');
        $('.descrptionToSave' + rowNo).val('');
        $('.categoryToSave' + rowNo).val('');

        $('.uomToSave' + rowNo).val('');
        $('.unitPriceToSave' + rowNo).val('');

        $('.itemidToSave' + rowNo).val('');
        $('.quantityToSave' + rowNo).val('');
        $('.totalPriceToSave' + rowNo).val('');
       
        totalCal(rowNo);
    }
    function ResetContactType(rwcnt) {

        var type = $('.saveLineType' + rwcnt).val();

        if (type != "") {
            $.getJSON("/Quotation/SetCookie/", { type: type }, function (data) {

            });
        }
        GetLineTypeValue(rwcnt);
    }
    function CheckValidData(count) {

        var linetype = $('.saveLineType' + count).val();

        if (linetype == "") {
            msg_box('Please Select Line Type First In Line ' + count, 'A', function (return_val) { });
           
            $('.saveLineType' + count).focus();
        }
    }


function SelectedRow(rowNo) {
    var lastRow = "";
    var nowRow = $("#rowCount").val();

    //var id = document.getElementById("address_id");
    //var x = $("#address_id"+ rowNo).val();        
    nowRow = rowNo;

    $('#sr_' + lastRow).css('background-color', '');
    lastRow = rowNo;
    $('#sr_' + nowRow).css('background-color', '#BCC6D7');

    getOtherDetails(rowNo);
}

function sup_site_bill_ship_to() {
    var supplier_addressId = $('#sup_address_id').val();
    var SupplierId = $('#sup_id').val();

    var Phone = "";
    var Email = "";
    var FreightTerm = "";
    var PaymentTerm = "";
    var FOB = "";
    var carrier = "";
    $.getJSON('/Quotation/FromSupplierBillShipAddress/', { supplier_addressID: supplier_addressId, SupplierId: SupplierId }, function (data) {
        Phone = data.phone;
        Email = data.email;
        FreightTerm = data.FreightTerm;
        PaymentTerm = data.PaymentTerm;
        FOB = data.FOB;
        carrier = data.carrier;
        $("#bill_to_address").val(Phone);


        $("#ship_to_address").val(Email);
        $("#savePaymentTerms").val(PaymentTerm);
        $("#saveFreightTerms").val(FreightTerm);
        $("#saveCarrier").val(carrier);
        $("#saveSupFob").val(FOB);
    });
}



function SelectItem(rowNo) {

    var itemData = $('.items' + rowNo).val();
    var saveLineType = $('.saveLineType' + rowNo).val();
    if (itemData == "") {
        itemData = $('.items2' + rowNo).val();
    }
    var str = itemData.split('~');
    if (str.length > 1) {
        var items = str[0];
        var desc = str[1];
        var cat = str[2];
        var uom = str[3];
        var unitPrice = str[4];

        var item_id = str[5];
        var qty = 1;
        var linetotal = parseInt(unitPrice) * parseInt(qty);
        var QuotTotal = $("#quotation_header_amt").val();


        var total = parseInt(QuotTotal) + parseInt(linetotal); ;
        if (saveLineType == 264) {
            $('.items' + rowNo).val(items);
        }
        if (saveLineType == 265) {
            $('.items2' + rowNo).val(items);
            $('.items' + rowNo).val(items);
        }
        $('.descrptionToSave' + rowNo).val(desc);

        $('.categoryToSave' + rowNo).val(cat);

        $('.uomToSave' + rowNo).val(uom);
        $('.unitPriceToSave' + rowNo).val(unitPrice);

        $('.itemidToSave' + rowNo).val(item_id);
        $('.quantityToSave' + rowNo).val('1');
        if (isNaN(linetotal)) {
            $('.totalPriceToSave' + rowNo).val(0);
        }
        else {
            $('.totalPriceToSave' + rowNo).val(linetotal);
        }
        $("#quotation_header_amt").val(total);

        lastItem = items;
        if (adHoc == "1") {
            $('.unitPriceToSave' + rowNo).attr('readonly', false);
        } else {
            $('.unitPriceToSave' + rowNo).attr('readonly', true);
        }

        var quantity = $('.quantityToSave' + rowNo).val();

        var totPrice = "";
        //==================
        if (quantity != "") {
            totPrice = parseInt(quantity) * parseInt(unitPrice);
            $('.totalPriceToSave' + rowNo).val(totPrice);
        } else {
            $('.quantityToSave' + rowNo).val('0');
            $('.totalPriceToSave' + rowNo).val('0');
        }

        var vat = $('.vatToSave' + rowNo).val();
        //====================
        if (vat != "") {
            unitPrice = parseInt($('.totalPriceToSave' + rowNo).val());
            var final = parseInt((unitPrice * vat) / 100);
            $('.taxAmountToSave' + rowNo).val(final);
        } else {
            $('.vatToSave' + rowNo).val('0');
            $('.taxAmountToSave' + rowNo).val('0');
        }

        //==========================
        if (discount != "") {

            var totalp = parseInt($('.totalPriceToSave' + rowNo).val()) + parseInt($('.taxAmountToSave' + rowNo).val());
            var finalPrice = (totalp * (100 - discount)) / 100;
            $('.finalPriceToSave' + rowNo).val(finalPrice);
        } else {
            $('.finalPriceToSave' + rowNo).val('0');
        }



    }
}



function Quantity(count, id) {

    var total = "";
    var quantity = $('.quantityToSave' + count).val();

    var lastValue = $("#getCurrentValue").val();
    var QuotationTotal = $("#quotation_header_amt").val();

    //--- If it is not a number
    if (isNaN(quantity)) {
        $('.quantityToSave' + count).val('');
        $('.quantityToSave' + count).attr('placeholder', 'Enter Valid Number');
        $('.quantityToSave' + count).focus();
        $('.quantityToSave' + count).css('border-color', 'red');
        $('.quantityToSave' + count).css('background-color', '#FFE8A6');

    } else {
        $('.quantityToSave' + count).css('border-color', '');
        $('.quantityToSave' + count).css('background-color', '');
        $('.quantityToSave' + count).attr('placeholder', '');
        if ($('.unitPriceToSave' + count).val() != "" && $('.quantityToSave' + count).val() != "") {
            total = $('.unitPriceToSave' + count).val() * $('.quantityToSave' + count).val();
            $('.totalPriceToSave' + count).val(total);

            ReqTotal = $('.unitPriceToSave' + count).val() * parseInt(lastValue);
            var result = parseInt(QuotationTotal) - parseInt(ReqTotal);

            var finalResult = parseInt(result) + parseInt(total);

            $("#quotation_header_amt").val(finalResult);
            //--- Calculate tax amount if vat is present 
            var vat = $('.vatToSave' + count).val();
            if (vat != "") {

                var unitPrice = parseInt($('.totalPriceToSave' + count).val());

                var final = parseInt((unitPrice * vat) / 100);
                $('.taxAmountToSave' + count).val(final);
                //--------
                // --------- calculate final price if discount is present
                var discount = $('.discountToSave' + count).val();
                if (discount != "") {
                    var totalp = parseInt($('.totalPriceToSave' + count).val()) + parseInt($('.taxAmountToSave' + count).val());
                    final = (totalp * (100 - discount)) / 100;
                    $('.finalPriceToSave' + count).val(final);

                }
            }
        } else {
            $('.totalPriceToSave' + count).val('');
        }
    }


}
function GetQuoteSupplierSite() {
  
    var s = $('#supplier').val();
   
    var listItem = "<option>" + "--Select--" + "</option>";

    var supId = "";
    if (s.trim() != "") {

     
          $.getJSON('/Quotation/GetSupplierSite/', { id: s }, function (data) {
            $.each(data.list, function (i, txt) {
                listItem += "<option Value=" + txt.Value + ">" + txt.Text + "</option>";
            });
            supId = data.supId;
            $('#sup_address_id').html(listItem);
            $('#sup_id').val(supId);
          
          
        });
    }
}
