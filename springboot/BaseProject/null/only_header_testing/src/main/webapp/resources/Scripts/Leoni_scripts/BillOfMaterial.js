

function SelectItemCopyBom() {

    $.ajaxSetup({ cache: false });
    var itemData = $('#bom_name').val();
  
        var str = itemData.split('~');

        if (str.length > 1) {
            var items = str[0];
            var copyitem_id = str[1];
            var copybom_id = str[2];
          
            $("#bom_name").val(items);

            $("#copyitem_id").val(copyitem_id);
            $("#copybom_id").val(copybom_id);
        }
    
    //setTimeout(fuct1,100);
}


/////////////////////////////////// Header Split fuction ///////////////////////////////

function SelectItem() {

    $.ajaxSetup({ cache: false });
    var itemData = $('#bom_item').val();

    var fuct2 = function () {
        var str = itemData.split('~');

        if (str.length > 1) {
            var items = str[0];
            var desc = str[1];
            var item_id = str[2];
            var uom = str[3];
            var revision = str[4];

            $('#bom_item').val(items);
            var id = $('#item_id').val(item_id);

            $('#bom_description').val(desc);

            $("#bom_uom").val(uom);
            $("#bom_revision").val(revision);
        }
    }
    setTimeout(fuct2, 1000);
}



 


  /////////////////////////////////// replace image ///////////////////////////////
  function replaceSrc(RowCount) {

        $.ajaxSetup({ cache: false });
        for (var j = 1; j <= RowCount; j++) {
            var images = document.getElementsByName('img ' + j);

            for (var i = 0; i < images.length; i++) {
                var img = images[i];

                if (img.src.length > 0) {

                    img.src = ' ';
                }
            }
        }
    }
    /////////////////////////////////// Line-Item Split  ///////////////////////////////
    function getSubAssembly(rowcnt) {

        
     
        $.ajaxSetup({ cache: false });
        var bom_item = $('#bom_item').val();

        var itemData = $('.saveComponent' + rowcnt).val();
        $('.saveYield' + rowcnt).val('1');
       
        if (rowcnt > 1) {

            var privRowcount = rowcnt - 1;
            var saveComponent = $('.saveComponent' + privRowcount).val();

            if (saveComponent != null && saveComponent != "") {
                var itemData = $('.saveComponent' + rowcnt).val();
               
                    var str = itemData.split('~');
               
                    if (str.length > 0) {

                        var items = str[0];

                        if (bom_item == items || saveComponent == items) {


                            $('.saveComponent' + rowcnt).val('');
                            $(".saveComponent" + rowcnt).focus();
                        }

                        else {
                         
                            var itemData1 = $('.saveComponent' + rowcnt).val();


                            var str1 = itemData1.split('~');
                            var items1 = str1[0];

                            var itemid1 = str1[1];

                            var itemtype1 = str1[2];


                            var itemdescription1 = str1[3];

                            var uom1 = str1[4];

                            var rev1 = str1[5];

                            var bomsubinv1 = str1[6];


                            var bomloc1 = str1[7];

                            var bomtype = str1[8];
                           
                            $('.saveComponent' + rowcnt).val(items1);
                            var id = $('.saveItemid' + rowcnt).val(itemid1);

                            $('.saveItemDescription' + rowcnt).val(itemdescription1);
                            $('.item_type' + rowcnt).val(itemtype1);
                            $('.saveUOM' + rowcnt).val(uom1);
                            $('.saveRevision' + rowcnt).val(rev1);

                            $('.saveSubinventory' + rowcnt).val(bomsubinv1);
                            $('.saveLocator' + rowcnt).val(bomloc1);

                            $('.savesupplytype' + rowcnt).val(bomtype);

                            if (itemtype1 == 404) {

                                var images = document.getElementsByName('.image_bom ' + rowcnt);

                                for (var i = 0; i < images.length; i++) {
                                    var img = images[i];

                                    if (img.src.length > 0) {
                                        img.src = '../../Content/images/document-arrow-up-icon2.png';
                                    }

                                }
                            }
                      
                    }



                }


            }

        }



        if (rowcnt == 1) {


            var str = itemData.split('~');
         
            if (str.length > 0) {
           
                var items = str[0];

                var itemid = str[1];

                var itemtype = str[2];

             
                var itemdescription = str[3];

                var uom = str[4];
              
                var rev = str[5];

                var bomsubinv = str[6];

        
                var bomloc = str[7];
                
                var bomtype = str[8];
       
                $('.saveComponent' + rowcnt).val(items);
                var id = $('.saveItemid' + rowcnt).val(itemid);

                $('.saveItemDescription' + rowcnt).val(itemdescription);
                $('.item_type' + rowcnt).val(itemtype);
                $('.saveUOM' + rowcnt).val(uom);
                $('.saveRevision' + rowcnt).val(rev);

                $('.saveSubinventory' + rowcnt).val(bomsubinv);
                $('.saveLocator' + rowcnt).val(bomloc);
                $('.savesupplytype' + rowcnt).val(bomtype);
                if (itemtype == 404) {

                    var images = document.getElementsByName('updateimg ' + rowcnt);
                   
                    for (var i = 0; i < images.length; i++) {

                 

                        var img = images[i];
                    
                        if (img.src.length > 0) {

                            img.src = '../../Content/images/document-arrow-up-icon2.png';
                          

                        }
                    }
                }

            }



            else {
                replaceSrc(rowcnt);
            }
            GetLocator(bomsubinv);
        }




        ///////////////////////////////////  Get Locator ///////////////////////////////

        function GetLocator(id) {
            $.ajaxSetup({ cache: false });
            var a = id;

            var listItem = "-Select-";
            $.getJSON('/BillOfMaterial/GetLocator/', { subInventoryID: a }, function (data) {

                $.each(data, function (i, txt) {
                    listItem += "<option Value=" + txt.Value + ">" + txt.Text + "</option>";


                });
                $('.saveLocator' + rowcnt).html(listItem);



            });

            GetAddtionalValue(id);
        }

    }

      ///////////////////////////////////  OP validation ///////////////////////////////

 function CheckOPCode(RowCount) {

        $.ajaxSetup({ cache: false });
    
        if (RowCount > 1) {
            var privRowcount = RowCount - 1;
            var saveOP = $('.saveOP' + privRowcount).val();

        }


        if (saveOP != null && saveOP != "") {

            for (var j = 1; j <= RowCount; j++) {

                var nextOp = $(".saveOP" + j).val();
                var message = "Select Same OP Code";
                if (saveOP != nextOp) {

                    $.messager.alert('Bill Of Material', message);

                    $(".saveOP" + j).val("");
                    $(".saveOP" + j).focus();

                }

            }
        }
    }

       ///////////////////////////////////  Graph  ///////////////////////////////
    function ShowGraph(id) {


        var bom_line_component = $("#bom_item").val();
      
//        var str = bom_line_component.replace(/ /, "");
        var str = bom_line_component;
  
        retval = window.showModalDialog('/BillOfMaterial/SubAssemblyBOM/?bom_line_component=' + str, "WindowPopup", 'width=500px,height=500px');


    }


    
    //######################### Date Compare Function ##############################

    function CompareEndDate() {
        
        var StartDate = $('#bom_start_date').val();
        CheckDate('bom_end_date');
        var EndDate = $('#bom_end_date').val();
        var inputToDate = Date.parse(EndDate);
        var todayToDate = Date.parse(StartDate);


        if (inputToDate < todayToDate) {
            $('#bom_end_date').val('');
            $('#bom_end_date').css('border-color', 'red');
            $('#bom_end_date').css('background-color', '#FFE8A6');
            $('#bom_end_date').attr('placeholder', 'Invalid Date');
            $('#bom_end_date').focus();

        }
        else {
            $('#bom_end_date').css('border-color', '');
            $('#bom_end_date').css('background-color', '');
            $('#bom_end_date').val(EndDate);

        }

      

    }
    //######################LineValidate####################################
    function LineValidate() {
        var data = 0;
        var RowCount = $("#rowcnt").val();

        RowCount = RowCount - 1;
       
        for (var i = 1; i <= RowCount; i++) {
            if ($(".saveOP" + i).val() != "") {
              
                var OP = $(".saveOP" + i).val();
               
                var Component = $(".saveComponent" + i).val();
                
                var Alter_no = $(".rowNums3" + i).val();
              
                var UOM = $(".saveUOM" + i).val();
                var quantity = $(".rowNum4" + i).val();
               
                var planning = $(".savePlanning" + i).val();
                
                var charge_type = $(".saveChargeType" + i).val();
                var supplytype = $(".savesupplytype" + i).val();
                //  var date = document.getElementById('saveBomStartDate ' + i);
                if (OP == "") {

                    var message = "Enter OP in Line" + i;
                    $.messager.alert('Bill Of Material', message);
                    exit();

                }

                if (Component == "") {

                    var message = "Enter Component in Line" + i;
                    $.messager.alert('Bill Of Material', message);
                    exit();
                }
                if (Alter_no == "") {

                    var message = "Enter Alternate number in Line" + i;
                    $.messager.alert('Bill Of Material', message);
                    exit();
                }
                if (UOM == "") {

                    var message = "Enter UOM in Line" + i;
                    $.messager.alert('Bill Of Material', message);
                    exit();
                }
                if (quantity == "") {

                    var message = "Enter Quantity in Line" + i;
                    $.messager.alert('Bill Of Material', message);
                    exit();
                }
                if (planning == "") {

                    var message = "Enter Planning in Line" + i;

                    $.messager.alert('Bill Of Material', message);
                    exit();
                }
                if (charge_type == "") {

                    var message = "Enter Charge type in Line" + i;
                    $.messager.alert('Bill Of Material', message);
                    exit();
                }
                if (supplytype == "") {

                    var message = "Enter Type in Line" + i;
                    $.messager.alert('Bill Of Material', message);
                    exit();
                }

            }
            else {
                data = data + 1;


            }

        }

        return data;

    }





    //######################### Load New #################################


    function loadNew() {
        $.ajaxSetup({ cache: false });
        var j = 0;

        $.ajax(
            {

                type: "GET",
                url: '@Url.Action("BillOfMaterial", "BillOfMaterial")',
                //data: "&command=" + command + "&CreateItemToFind=" + CreateItemToFind,

                success: function (htmlPartialView) {
                    $("#content").html(htmlPartialView);


                },
                error: function (xhr, textStatus, errorThrown) {

                }
            });
        }
     

     //##################### Check Grid Number ##################################
        function checkGridNum(rowCount) {

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
                $('.rowNum' + rowCount).focus();

                $('.rowNum' + rowCount).css('border-color', '');
                $('.rowNum' + rowCount).css('background-color', '');

            }

        }
        //###################### ColorOnEmpty ###############################

        function ColorOnEmpty(ctrlName) {

            var value = $('#' + ctrlName).val().trim();

            if (value == "") {
                $('#1' + ctrlName).css('color', 'red');

            } else {

                $('#1' + ctrlName).css('color', '');
            }
        }

        //##################### IsDataValid ###############################
       
        function IsDataValid() {

            var flag = 0;
            var array = new Array('bom_item', 'bom_alternate_no', 'bom_description', 'bom_start_date', 'bom_uom', 'bom_inv_class');

            flag = IsControlValid(array);

            return flag;
        }


        //######################### IsControlValid #####################

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

        //########################## calldialog  ####################
        function calldialog(cnt) {

            var bom_line_component = $(".saveComponent" + cnt).val();


            var str = bom_line_component.replace(/ /, "");

            retval = window.showModalDialog('/BillOfMaterial/SubAssemblyBOM/?bom_line_component=' + str, "WindowPopup", 'width=600px,height=600px');
        }

       
        //########################  GetItemValidation  ######################## 

        function getItemValidation(cnt, itemid) {

            var alert_box = $("#alert_box").show();
            var Item_Id = $("#item_id").val();
            var line_item_id = itemid;
            var Message = "";
            var Message1 = "";


            $.getJSON('/BillOfMaterial/CheckBOMValidation/', { Item_Id: Item_Id, line_item_id: line_item_id }, function (data) {

                Message = data.Message;

                if (Message == "False") {


                    msg_box("Invalid Material", 'A', function (return_val) { });
                    $('.saveComponent' + cnt).val('');
                    $('.saveItemDescription' + cnt).val('');
                    $('.saveAlternateNo' + cnt).val('');
                    $('.saveUOM' + cnt).val('');
                    $('.saveRevision' + cnt).val('');
                    $('.saveSubinventory' + cnt).val('');
                    $('.saveLocator' + cnt).val('');

                }
                else if (Message == "True") {


                }


            });


        }

        function ClearLines() {
            var GetCurrentRow = $("#RowForData").val();
            $(".saveItemid" + GetCurrentRow).val('');
            $(".item_type" + GetCurrentRow).val('');
            $(".SaveSequence" + GetCurrentRow).val('');
            $(".saveOP" + GetCurrentRow).val('');
            $(".saveComponent" + GetCurrentRow).val('');
            $(".saveItemDescription" + GetCurrentRow).val('');
            $(".rowNums3" + GetCurrentRow).val('');
            $(".saveUOM" + GetCurrentRow).val('');
            $(".rowNum4" + GetCurrentRow).val('');
            $(".saveRevision" + GetCurrentRow).val('');
            $(".savePlanning" + GetCurrentRow).val('');
            $(".savesupplytype" + GetCurrentRow).val('');
            $(".saveYield" + GetCurrentRow).val('');           
            $(".saveNegativeType" + GetCurrentRow).val('');
            $(".saveChargeType" + GetCurrentRow).val('');
            $(".saveSubinventory" + GetCurrentRow).val('');                 
            $(".saveCheck_ATP" + GetCurrentRow).attr("checked", false);
            $(".saveOptional" + GetCurrentRow).attr("checked", false);
            $(".saveMutually_Exclusive" + GetCurrentRow).attr("checked", false);
            document.getElementById("saveLocator " + GetCurrentRow).value = '';
            document.getElementById("saveBomStartDate " + GetCurrentRow).value = '';
            document.getElementById("saveBomEndDate " + GetCurrentRow).value = '';        
        }

        function CopyAbove() {
            var GetCurrentRow = $("#RowForData").val();
            var PreviousRow = parseInt(GetCurrentRow) - 1;

            var saveItemid = $(".saveItemid" + PreviousRow).val();
            var item_type = $(".item_type" + PreviousRow).val();
            var SaveSequence = $(".SaveSequence" + PreviousRow).val();
            var saveOP = $(".saveOP" + PreviousRow).val();
            var saveComponent = $(".saveComponent" + PreviousRow).val();
            var saveItemDescription = $(".saveItemDescription" + PreviousRow).val();
            var rowNums3 = $(".rowNums3" + PreviousRow).val();
            var saveUOM = $(".saveUOM" + PreviousRow).val();
            var rowNum4 = $(".rowNum4" + PreviousRow).val();
            var saveRevision = $(".saveRevision" + PreviousRow).val();
            var savePlanning = $(".savePlanning" + PreviousRow).val();
            var saveYield = $(".saveYield" + PreviousRow).val();
            var savesupplytype = $(".savesupplytype" + PreviousRow).val();
            var saveNegativeType = $(".saveNegativeType" + PreviousRow).val();
            var saveChargeType = $(".saveChargeType" + PreviousRow).val();
            var saveSubinventory = $(".saveSubinventory" + PreviousRow).val();
            var saveLocator = $(".saveLocator" + PreviousRow).val();
            var saveCheck_ATP = $("#saveCheck_ATP" + PreviousRow).is(':checked');
            var saveOptional = $("#saveOptional" + PreviousRow).is(':checked');
            var saveMutually_Exclusive = $("#saveMutually_Exclusive" + PreviousRow).is(':checked');

            var saveBomStartDate = document.getElementById('saveBomStartDate ' + PreviousRow).value;
            var saveBomEndDate = document.getElementById('saveBomEndDate ' + PreviousRow).value;

            $(".saveItemid" + GetCurrentRow).val(saveItemid);
            $(".item_type" + GetCurrentRow).val(item_type);
            $(".SaveSequence" + GetCurrentRow).val(SaveSequence);
           $(".saveOP" + GetCurrentRow).val(saveOP);
            $(".saveComponent" + GetCurrentRow).val(saveComponent);
            $(".saveItemDescription" + GetCurrentRow).val(saveItemDescription);
            $(".rowNums3" + GetCurrentRow).val(rowNums3);
            $(".saveUOM" + GetCurrentRow).val(saveUOM);
            $(".rowNum4" + GetCurrentRow).val(rowNum4);
            $(".saveRevision" + GetCurrentRow).val(saveRevision);
            $(".savePlanning" + GetCurrentRow).val(savePlanning);
            $(".savesupplytype" + GetCurrentRow).val(savesupplytype);
            $(".saveYield" + GetCurrentRow).val(saveYield);
            $(".savesupplytype" + GetCurrentRow).val(savesupplytype);
            $(".saveChargeType" + GetCurrentRow).val(saveChargeType);
            $(".saveNegativeType" + GetCurrentRow).val(saveNegativeType);
            $(".saveSubinventory" + GetCurrentRow).val(saveSubinventory);
            $(".saveLocator" + GetCurrentRow).val(saveLocator);
            $("#saveOptional" + GetCurrentRow).prop("checked", saveOptional);
            $("#saveCheck_ATP" + GetCurrentRow).prop( "checked", saveCheck_ATP );           
            $("#saveMutually_Exclusive" + GetCurrentRow).prop("checked", saveMutually_Exclusive);
           


            document.getElementById("saveBomStartDate " + GetCurrentRow).value = saveBomStartDate;
            document.getElementById("saveBomEndDate " + GetCurrentRow).value = saveBomEndDate;

        }
