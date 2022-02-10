function CompareEndDate() {

    var EndDate = $('#requisition_end_date').val();
    var StartDate = $('#requisition_date').val(); ;

   

    var inputToDate = Date.parse(EndDate);
    var todayToDate = Date.parse(StartDate);

    if (inputToDate < todayToDate) {
        $('#requisition_end_date').val('');
        $('#requisition_end_date').css('border-color', 'red');
        $('#requisition_end_date').css('background-color', '#FFE8A6');
        $('#requisition_end_date').attr('placeholder', 'Invalid Date');
        $('#requisition_end_date').focus();

    }
    else {

        $('#requisition_end_date').val(EndDate);

    }


}
    function LineValidate() {
        var data = 0;
        var RowCount = $("#RowC").val();
        RowCount = RowCount - 1;
        var alertbox = $("#alert_box").show();

       

        for (var i = 1; i <= RowCount; i++) {
            if ($(".saveLineType" + i).val() != "") {
                var item = $(".items" + i).val();
                var needbydate = $(".rowDate" + 10 + i).val();
                var totalPrice = $(".totalPriceToSave" + i).val();
                if (item == "") {

//                    msg_box('Enter Item in Line'+ i, 'A', function (return_val) { });

                    //                    $(".items" + i).focus()

                    var message = "Enter Item in Line" + i;
                    $.messager.alert('Create Requisition', message);
                    $(".items" + i).focus()

                    exit();
                }
                if (needbydate == "") {
//                    msg_box('Enter Need By Date in Line' + i, 'A', function (return_val) { });
//                   
//                    $(".rowDate" + 10 + i).focus()

//                    exit();

                    var message = "Enter Need By Date in Line" + i;
                    $.messager.alert('Create Requisition', message);
                    $(".rowDate" + 10 + i).focus()
                    exit();
                }
                if (totalPrice == 0) {
                    var message = "Enter Quantity and Unit Price for selected items" + i;
                    $.messager.alert('Create Requisition', message);
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
        var user = $("#User_id").val();

        var LineType = $(".saveLineType" + rowNo).val();

        var reqTotal = $("#requisition_total").val();
        var toalprice = $('.totalPriceToSave' + rowNo).val();
        $('.saveBuyer' + rowNo).val(user);
        $('.saveStatus' + rowNo).val('P');
        
        var zero = '0';
        
        if (reqTotal > zero && toalprice!="") {
            
            var total = parseInt(reqTotal) - parseInt(toalprice);
            $("#requisition_total").val(total);
        
        }
                
       
        $('.items' + rowNo).val('');
        $('.descrptionToSave' + rowNo).val('');
        $('.categoryToSave' + rowNo).val('');

        $('.uomToSave' + rowNo).val('');
        $('.unitPriceToSave' + rowNo).val('');

        $('.itemidToSave' + rowNo).val('');
        $('.quantityToSave' + rowNo).val('0');
        $('.totalPriceToSave' + rowNo).val('');
        //$("#requisition_total").val('');

       
        totalCal(rowNo);
    }









    function Quantity(count) {
        //  CheckOnHand(count);
        if ($('.quantityToSave' + count).val() != 0) {

            var total = 0;
            var quantity = 0; //$('.quantityToSave' + count).val();
            if ($('.quantityToSave' + count).val() != "") {
                
                quantity = $('.quantityToSave' + count).val();
            }
            var Rate = 0; //$('.unitPriceToSave' + count).val();
            if ($('.unitPriceToSave' + count).val() != "") {
                Rate = $('.unitPriceToSave' + count).val();
            }
            var RowCount = $("#RowC").val();
            var Row = RowCount - 1;
            //       
            //        var lastValue = $("#getCurrentValue").val();
            //        var LastRate = $("#getCurrentValueRate").val();
            //        if (lastValue == "") {
            //            lastValue = 0;
            //        }
            var RequisitionTotal = 0;
            // RequisitionTotal = RequisitionTotal - parseInt(LastRate);
            //--- If it is not a number
            if (isNaN(quantity)) {

                $('.quantityToSave' + count).val('');

            } else {
                if ($('.unitPriceToSave' + count).val() != "" && $('.quantityToSave' + count).val() != "") {

                    total = Rate * quantity;  //$('.unitPriceToSave' + count).val() * $('.quantityToSave' + count).val();
                    
                    $('.totalPriceToSave' + count).val(total);
                    for (var i = 1; i < Row; i++) {

                        var Total = 0;
                        if ($('.totalPriceToSave' + i).val() != "") {
                            Total = $('.totalPriceToSave' + i).val();
                        }
                        if (Total != "NaN" && typeof Total != 'undefined') {


                            if (Total == 0) {
                                RequisitionTotal = RequisitionTotal + 0;
                            }
                            else {
                                RequisitionTotal = RequisitionTotal + parseInt(Total);
                            }
                        }
                    }

                    $("#requisition_total").val(RequisitionTotal);

                }

            }



        }
        else {
            $.messager.alert('Create Requisition', 'Please Enter Valid Quantity!');
            $('.quantityToSave' + count).val(1)
        }

    }


    function confirm_box(id) {


        $.ajaxSetup({ cache: false });

        if (id == "Save") {
            $.messager.confirm('Create Requisition', 'Are you Sure You Want To Save this?', function (r) {
                if (r) {
                    Save_Operations(id);
                }
            });
        }

        else {
            $.messager.alert('Create Requisition', 'Please Enter Valid Data!');
        }
    }

  


 


 
    function getValue(count, id) {
        var quantity = $('.quantityToSave' + count).val();
        var Rate = $('.unitPriceToSave' + count).val();
        if (Rate > 0) {
            $("#getCurrentValue").val(quantity);
            $("#getCurrentValueRate").val(Rate);
        }
        else {
            var qty = 0;
            $("#getCurrentValue").val(qty);
            $("#getCurrentValueRate").val(Rate);
        }
    }
    function IsDataValid() {

        var flag = 0;

        var array = new Array('requisition_receiving_type', 'requisition_location', 'requisition_operating_unit');
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
            var qty = 0;

            var Rec_type = str[13];

            if (unitPrice == "") {
                unitPrice = 0;
                var linetotal = parseInt(unitPrice) * parseInt(qty);

            }
            else {
                var linetotal = parseInt(unitPrice) * parseInt(qty);
            
            }



            if (saveLineType == 264) {
                $('.items' + rowNo).val(items);
                
            }
            if (saveLineType == 265) {
                $('.items2' + rowNo).val(items);
                $('.items' + rowNo).val(items);

            }
            if (saveLineType == 266) {
                $('.items2' + rowNo).val(items);
                $('.items' + rowNo).val(items);

            }
            $('.descrptionToSave' + rowNo).val(desc);
            $('.categoryToSave' + rowNo).val(cat);

            $('.uomToSave' + rowNo).val(uom);
            $('.unitPriceToSave' + rowNo).val(unitPrice);

            $('.itemidToSave' + rowNo).val(item_id);
            $('.quantityToSave' + rowNo).val('0');
            $('.totalPriceToSave' + rowNo).val(linetotal);
            $(".save_requisition_receiving_type" + rowNo).val(Rec_type);
           
            totalCal(rowNo);

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
      //IsDupblicateItem(rowNo)
    }


//    function IsDupblicateItem(rowNo) {
//        
//        if (rowNo > 1) {
//            var itemName = $(".items" + rowNo).val();
//            
//            for (var i = rowNo-1; i >= 1; i--) {
//                var existItem = $(".items" + i).val();
//                
//                if (itemName == existItem) {
//                    $.messager.alert('Create Requisition', 'Dupblicate Item Entry is not allowed.');
//                    ClearLines();
//                    break;
//                }
//            }
//        }
//    }


    function totalCal(rowNo) {
      
        var reqTotal = $("#requisition_total").val();
        var rowcount1 = $("#RowC").val();
        reqTotal = 0;
        var itemTotal = 0;
        

        var tepTotal = 0;
        for (var i = 1; i < rowcount1; i++) {
            if ($('.totalPriceToSave' + i).val() != "") {
                itemTotal = $('.totalPriceToSave' + i).val();
                if (itemTotal != "") {
                    if (isNaN(itemTotal)) {
                        itemTotal = 0;
                    }
                tepTotal = parseInt(tepTotal) + parseInt(itemTotal);
            }
            //alert("itemTotal Fun " + itemTotal);
        
                $("#requisition_total").val(tepTotal);

            }

        }
    
    
    }


    function getdeptData() {
        var emp_name = $("#requisition_by").val();


        var listItem = "";
        $.getJSON('/Requisition/FindDepartment/', { emp_name: emp_name }, function (data) {
            listItem = data.dept;

            $("#requisition_dept").val(listItem);

        });

    }
 function ReqNumber() {

     

        var listItem = "";
        $.getJSON('/Requisition/GetReqNumber/', {}, function (data) {
            listItem = data.dept;
            var alert_box = $("#alert_box").show();
            $("#requisition_no").val(listItem);
            msg_box('Requisition Created Number is' + listItem, 'A', function (return_val) { });
        });

    }
    function CheckValidData(count) {
       
        var linetype = $('.saveLineType' + count).val();
        var alert_box = $("#alert_box").show();
        if (linetype == "") {
            msg_box('Please Select Line Type First In Line ' + count, 'A', function (return_val) { });
            $('.saveLineType' + count).focus();
        }
    }
    function CheckOnHand(count) {
        var listItem = "";
        var Item = $('.itemidToSave' + count).val();
        var itemqty = $('.quantityToSave' + count).val();
        $.getJSON('/Requisition/GetOnHand/', { item_id: Item }, function (data) {
            listItem = data.dept;
            var alert_box = $("#confirm_box").show();
            // $("#requisition_no").val(listItem);
            //            msg_box('Available Quantity is' + listItem, 'A', function (return_val) { });
            //            if (itemqty > listItem) {

            //                Quantity(count);
            //            }
            //            else {
            //                
            //                GetLineTypeValue(count);
            //                $('.quantityToSave' + count).val('');
            //                $('.saveLineType' + count).val('');
            //            }
            if (listItem > itemqty) {
                msg_box('Available Quantity is ' + listItem + ' Do you want to Continue', 'C', function (return_val) {
                    if (return_val == 'yes') {
                        Quantity(count);
                        $('.unitPriceToSave' + count).focus();
                    }
                    else {
                        GetLineTypeValue(count);
                        $('.quantityToSave' + count).val('');
                        $('.saveLineType' + count).val('');
                    }

                });
            }
            else {
                var alert_box = $("#alert_box").show();
                Quantity(count);
                msg_box('Available Quantity is ' + listItem, 'A', function (return_val) { });
                $('.unitPriceToSave' + count).focus();
            }
        });
    }
    var lastRow = "";
    function SelectedRowData(rowNo) {

        $("#RowForData").val(rowNo);
        $('#sr_' + lastRow).css('background-color', '');
        lastRow = rowNo;
        $('#sr_' + rowNo).css('background-color', '#BCC6D7');
        var linedata = $(".saveLineType" + rowNo).val();
        if (linedata == "") {

            $(".saveLineType" + rowNo).val(264);
            ResetContactType(rowNo);
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
        totalCal(1);
    }

    function CopyAbove() {
        var GetCurrentRow = $("#RowForData").val();
       
        var PreviousRow = parseInt(GetCurrentRow) - 1;
       
        var save_requisition_receiving_type = $(".save_requisition_receiving_type" + PreviousRow).val();
        var saveLineType = $(".saveLineType" + PreviousRow).val();
        var items = $(".items" + PreviousRow).val();
        var itemidToSave = $(".itemidToSave" + PreviousRow).val();
        var descrptionToSave = $(".descrptionToSave" + PreviousRow).val();
        var uomToSave = $(".uomToSave" + PreviousRow).val();
        var quantityToSave = $(".quantityToSave" + PreviousRow).val();
        var unitPriceToSave = $(".unitPriceToSave" + PreviousRow).val();
        var totalPriceToSave = $(".totalPriceToSave" + PreviousRow).val();
        var saveBuyer = $(".saveBuyer" + PreviousRow).val();
        var BASSGridDate = document.getElementById('saveNeedByDate ' + PreviousRow).value;
       
        var saveStatus = $(".saveStatus" + PreviousRow).val();
        var savePONumber = $(".savePONumber" + PreviousRow).val();
        $(".saveLineType" + GetCurrentRow).val(saveLineType);
        $(".save_requisition_receiving_type" + GetCurrentRow).val(save_requisition_receiving_type);
        $(".items" + GetCurrentRow).val(items);

        $(".itemidToSave" + GetCurrentRow).val(itemidToSave);
        $(".descrptionToSave" + GetCurrentRow).val(descrptionToSave);
        $(".uomToSave" + GetCurrentRow).val(uomToSave);
        $(".quantityToSave" + GetCurrentRow).val(quantityToSave);
        $(".unitPriceToSave" + GetCurrentRow).val(unitPriceToSave);
        $(".totalPriceToSave" + GetCurrentRow).val(totalPriceToSave);

        $(".saveBuyer" + GetCurrentRow).val(saveBuyer);

       // $("#saveNeedByDate " + GetCurrentRow).val(BASSGridDate);
        document.getElementById("saveNeedByDate " + GetCurrentRow).value = BASSGridDate;
        $(".saveStatus" + GetCurrentRow).val(saveStatus);
        $(".savePONumber" + GetCurrentRow).val(savePONumber);



        totalCal(1);
    }

    function AddRow() {
        var str = "";
        var val = 0;
        var max = 6;
        var rowCount = 0;
        rowCount = parseInt($('#RowC').val());  // Get the last Row Value

        
        //$('#RowCountVal').val(rowCount)    //Append the new row value incrementing value by one

        var clone = $('#FirstRow').clone(true); //Copy the row as it is
       // $newParticipant = $("#FirstRow").clone(true)

        //  var rw = rowCount;

        //  Change the Id attribute of all controls

        clone.find("#srNumber").attr("id", "srNumber").val(rowCount);
        clone.find("#saveLineType").attr("id", "saveLineType").attr('class', 'saveLineType' + rowCount + ' BASSGridControl').val('').attr("onchange", "ResetContactType('" + rowCount + "')");
      
        clone.find("#save_requisition_receiving_type").attr('class', 'save_requisition_receiving_type' + rowCount + ' BASSGridControl').val('');
        clone.find("#saveitem").attr('hidden', 'hidden').attr('class', 'itemidToSave' + rowCount).val('');
        clone.find("#saveItemCode" + 1).attr("name", "saveItemCode" + rowCount).attr("id", "saveItemCode" + rowCount).attr('class', 'items' + rowCount + ' BASSGridControlmedium ui-autocomplete-input').val('').attr("onchange", "CheckValidItem(" + rowCount + ")").attr("onfocus", "CheckValidData(" + rowCount + ")");
       // $('#saveItemCode').autocomplete("destroy");
       
        clone.find("#saveDescription").attr('class', 'descrptionToSave' + rowCount + ' BASSGridControl').val('').attr('readonly', 'readonly');
        clone.find("#saveUOM").attr('class', 'uomToSave' + rowCount + ' BASSGridControl').val('').attr('readonly', 'readonly');
        
        clone.find("#saveItemQty").attr('class', 'quantityToSave' + rowCount + ' BASSGridControl').val('').attr('readonly', 'readonly');
        clone.find("#saveInitPrice").attr('class', 'unitPriceToSave' + rowCount + ' BASSGridControl').val('').attr('readonly', 'readonly');
        clone.find("#saveLineTotal").attr('class', 'totalPriceToSave' + rowCount + ' BASSGridControl').val('').attr('readonly', 'readonly');
        clone.find("#saveBuyer").attr('class', 'saveBuyer' + rowCount + ' BASSGridControl').val('').attr('readonly', 'readonly').attr('onclick', onclick = "NewCssCal(id, 'ddMMMyyyy', '', '', '', '', '');");
        clone.find("#saveNeedByDate ").attr("id", 'saveNeedByDate ' + rowCount).attr('class', 'saveNeedByDate' + rowCount + ' BASSGridControl').val('');
        clone.find("#saveStatus").attr('class', 'saveStatus' + rowCount + ' readonlyGreyColor').val('').attr('readonly', 'readonly');

        clone.find("#savePONumber").attr('class', 'savePONumber' + rowCount + ' readonlyGreyColor').val('').attr('readonly', 'readonly');


      //.attr("onkeyup","AutocompleteData("+rowCount+")")

    $('#ReqId').append(clone);
//    $('.items' + rowCount).autocomplete('<%=Url.Action("AutocompleteForRequisitionItemsGoodsOnPO", "AutoComplete") %>', {
//        autoFill: true,
//        selectFirst: true,
//        width: '240px',
//        minLength: 2,
//        delay: 300
//    });
   
       // jQuery.ready();
//         $('*[data-autocomplete-url]')
//        .each(function () {
//            $(this).autocomplete({
//           
//                source: $(this).data("autocomplete-url")
//            });

         rowCount += 1;

        $('#RowC').val('');
        $('#RowC').val(rowCount);
//        $().ready(initializationFunction);

//        // later:
        //        initializationFunction(jQuery);
       $(document).ready();
//        $(document).bind('_page_ready', function () { /* do your stuff here */
//          $('*[data-autocomplete-url]')
//        .each(function () {
//            $(this).autocomplete({
//           
//                source: $(this).data("autocomplete-url")
//            });
//         });
//        $(function () { $(document).fire('_page_ready'); }); // shorthand for document.ready

    }


    //    $('.itemidToSave').autocomplete('<%=Url.Action("AutocompleteForEmployeeName", "AutoComplete") %>', {
//        autoFill: true,
//        selectFirst: true,
//        width: '240px',
//        minLength: 2,
//        delay: 300
    //    });
   
