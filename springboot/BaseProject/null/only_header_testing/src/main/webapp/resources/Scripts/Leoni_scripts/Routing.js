
function SelectItemCopyRouting() {

    $.ajaxSetup({ cache: false });
    var itemData = $('#rout_name').val();

    var str = itemData.split('~');

    if (str.length > 1) {
        var items = str[0];
        var copyitem_id = str[1];
        var copyrout_id = str[2];
     
        $("#rout_name").val(items);

        $("#copyitem_id").val(copyitem_id);
        $("#copyrout_id").val(copyrout_id);
    }
}

//############# CompareEndDate ##################
function CompareEndDate() {
    $.ajaxSetup({ cache: false });
    var StartDate = $('#rout_start_date').val();
    CheckDate('rout_end_date');
    var EndDate = $('#rout_end_date').val();
    var inputToDate = Date.parse(EndDate);
    var todayToDate = Date.parse(StartDate);

    if (inputToDate < todayToDate) {
       
        $('#rout_end_date').val('');
        $('#rout_end_date').css('border-color', 'red');
        $('#rout_end_date').css('background-color', '#FFE8A6');
        $('#rout_end_date').attr('placeholder', 'Invalid Date');
        $('#rout_end_date').focus();
        //alert("End Date "+inputToDate +"Start Date:"+todayToDate);

    }
    else {
        $('#rout_end_date').css('border-color', '');
        $('#rout_end_date').css('background-color', '');
        $('#rout_end_date').val(EndDate);

    }
}

//##############  loadNew #######################

function loadNew() {
    var j = 0;
    $.ajax(
            {

                type: "GET",
                url: '@Url.Action("Routing", "Routing")',

                success: function (htmlPartialView) {
                    $("#content").html(htmlPartialView);


                },
                error: function (xhr, textStatus, errorThrown) {

                }
            });
}

//################  checkGridNum  ################

function checkGridNum(rowCount) {

    var num = $('.rowNum' + rowCount).val();

    //check if valid number
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

//####################### SelectItem #################################


function SelectItem(cnt) {

    var itemData = $('#rout_item').val();
    var str = itemData.split('~');
    if (str.length > 1) {
        var items = str[0];
        var desc = str[1]; desc

        var item_id = str[2];
        var uom = str[3];

        var rev = str[4];
        $('#rout_item').val(items);
        $('#rout_description').val(desc);
        $("#item_id").val(item_id);
        $("#rout_uom").val(uom);
        $("#rout_revision").val(rev);
      
        getOPName(item_id, cnt);
        //getSubItem(cnt);
    }
}

//################### GetComponent ###################


function getComponent(rowcnt) {
    var itemData = $('.saveComponent' + rowcnt).val();

    var str = itemData.split('~');

    if (str.length > 0) {

        var items = str[0];

        var itemid = str[1];


        var itemtype = str[2];

        var itemdescription = str[3];
        var uom = str[4];
        var rev = str[5];


        $('.saveComponent' + rowcnt).val(items);
        $('.saveitemid' + rowcnt).val(itemid);
        $('.saveItemDescription' + rowcnt).val(itemdescription);
        $('.saveUOM' + rowcnt).val(uom);
        $('.saveRevision' + rowcnt).val(rev);
    }

}

// #################  GetOPName #################
function getOPName(id) {

    var Sup_ID = $("#rout_item").val();
   
    var listItem = "<option>" + "" + "</option>";
    $.getJSON('/Routing/GetOP/', { rout_item: Sup_ID }, function (data) {
        $.each(data, function (i, txt) {

            listItem += "<option Value=" + txt.Text + ">" + txt.Text + "</option>";

        });
        for (var i = 0; i <= 10; i++) {

            $(".saveOP" + i).html(listItem);
        }


    });

}
// ###################### ColorOnEmpty ######################
 function ColorOnEmpty(ctrlName) {
        var value = $('#' + ctrlName).val().trim();

        if (value == "") {
            $('#1' + ctrlName).css('color', 'red');

        } else {
            $('#1' + ctrlName).css('color', 'black');
        }
    }
    // ####################  IsDataValid ####################
    function IsDataValid() {

        var flag = 0;
        var array = new Array('rout_item', 'rout_description', 'rout_alternate_no', 'rout_uom', 'rout_start_date');

        flag = IsControlValid(array);

        return flag;
    }
    // #################### IsControlValid ####################
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

    // ############### getSubItem ###############



    function getSubItem(cnt) {

        
        var Item_ID = $("#item_id").val();

        var Sup_ID_op = $(".saveOP" + cnt).val();

        $.getJSON('/Routing/GetComponent/', { item_id: Item_ID, op_code: Sup_ID_op }, function (data) {

            Sub_Assembly = data.Sub_Assembly;
            Item_Description = data.Item_Description;
            Sub_Assembly_Uom = data.Sub_Assembly_Uom;
            Sub_Assembly_Rev = data.Sub_Assembly_Rev;
            Item_Id = data.Item_Id;
          
            $(".saveComponent" + cnt).val(Sub_Assembly);
            $(".saveItemDescription" + cnt).val(Item_Description);
            $(".saveUOM" + cnt).val(Sub_Assembly_Uom);
            $(".saveRevision" + cnt).val(Sub_Assembly_Rev);
            $(".saveitem_id" + cnt).val(Item_Id);

        });

    }
    // ############LineValidate###########################


    function LineValidate() {
        var data = 0;
        var RowCount = $("#RowC").val();

        RowCount = RowCount - 1;


        for (var i = 1; i <= RowCount; i++) {

            if ($(".saveDepartments" + i).val() != "") {

                var OP = $(".saveOP" + i).val();

                var dept = $(".saveDepartments" + i).val();

                var wcenter = $(".saveWorkCenter" + i).val();

                var compo = $(".saveComponent" + i).val();

                var uom = $(".usaveUOMs" + i).val();
                var cycle = $(".saveCycleTime1" + i).val();
                var transitime = $(".saveOpTransiteTime2" + i).val();
                var leadtime = $(".rowNum" + i).val();
                var quantity = $(".rowNum" + i).val();

                if (OP == "") {

                    var message = "Enter OP in Line" + i;
                    $.messager.alert('Routing', message);


                    exit();
                }

                if (dept == "") {

                    var message = "Enter Department in Line" + i;

                    $.messager.alert('Routing', message);


                    exit();
                }
                if (wcenter == "") {


                    var message = "Enter Work Center in Line" + i;

                    $.messager.alert('Routing', message);


                    exit();
                }
                if (compo == "") {


                    var message = "Enter Compoment in Line" + i;

                    $.messager.alert('Routing', message);


                    exit();
                }
                if (uom == "") {

                    var message = "Enter UOM in Line" + i;

                    $.messager.alert('Routing', message);


                    exit();
                }
                if (cycle == "") {
                    var alertbox = $("#alert_box").show();
                    var message = "Enter CycleTime in Line" + i;

                    $.messager.alert('Routing', message);


                    exit();
                }
                if (transitime == "") {

                    var message = "Enter Transit Time in Line" + i;

                    $.messager.alert('Routing', message);


                    exit();
                }

                if (leadtime == "") {

                    var message = "Enter Lead Time in Line" + i;

                    $.messager.alert('Routing', message);


                    exit();
                }
                if (quantity == "") {

                    var message = "Enter Quantity in Line" + i;

                    $.messager.alert('Routing', message);


                    exit();

                }
                if (OP == "") {


                    var message = "Enter OP in Line" + i;
                    $.messager.alert('Routing', message);


                    exit();
                }



            }
            else {
                data = data + 1;


            }

        }
        return data;
    }
 