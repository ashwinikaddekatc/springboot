function Inventory() {

    var isChecked = ($('[name=effectiv_inventory]:checked').length);
    
    if (isChecked == 1) {

        $("#InventTab").show();
    }
    else {
        $("#InventTab").hide();
    }
}

function Purchasing() {

    var isChecked = ($('[name=effectiv_purchasing]:checked').length);
    
    if (isChecked == 1) {

        $("#PurchTab").show();
    }
    else {
        $("#PurchTab").hide();
    }
}

function OrderManage() {

    var isChecked = ($('[name=effectiv_order_management]:checked').length);
    
    if (isChecked == 1) {

        $("#OMTab").show();
    }
    else {
        $("#OMTab").hide();
    }
}

function Production() {

    var isChecked = ($('[name=effectiv_production]:checked').length);
    
    if (isChecked == 1) {

        $("#ProdTab").show();
    }
    else {
        $("#ProdTab").hide();
    }
}

function ServiceRequest() {

    var isChecked = ($('[name=effectiv_service_request]:checked').length);
    
    if (isChecked == 1) {

        $("#SRTab").show();
    }
    else {
        $("#SRTab").hide();
    }
}

function Planning() {

    var isChecked = ($('[name=effectiv_planning]:checked').length);
    
    if (isChecked == 1) {

        $("#PlanTab").show();
    }
    else {
        $("#PlanTab").hide();
    }
}

function Transportation() {

    var isChecked = ($('[name=effectiv_warehouse]:checked').length);

    if (isChecked == 1) {

        $("#WHTab").show();
    }
    else {
        $("#WHTab").hide();
    }
}

function BOM() {

    var isChecked = ($('[name=effectiv_bom]:checked').length);
    
    if (isChecked == 1) {

        $("#BOMTab").show();
    }
    else {
        $("#BOMTab").hide();
    }
}

$(document).ready(function () {

    var isChecked = ($('[name=effectiv_inventory]:checked').length);

    if (isChecked == 1) {

        $("#InventTab").show();
    }
    else {
        $("#InventTab").hide();
    }

    var isChecked = ($('[name=effectiv_purchasing]:checked').length);

    if (isChecked == 1) {

        $("#PurchTab").show();
    }
    else {
        $("#PurchTab").hide();
    }

    var isChecked = ($('[name=effectiv_bom]:checked').length);

    if (isChecked == 1) {

        $("#BOMTab").show();
    }
    else {
        $("#BOMTab").hide();
    }
            
    var isChecked = ($('[name=effectiv_order_management]:checked').length);

    if (isChecked == 1) {

        $("#OMTab").show();
    }
    else {
        $("#OMTab").hide();
    }

    var isChecked = ($('[name=effectiv_production]:checked').length);

    if (isChecked == 1) {

        $("#ProdTab").show();
    }
    else {
        $("#ProdTab").hide();
    }

    var isChecked = ($('[name=effectiv_service_request]:checked').length);

    if (isChecked == 1) {

        $("#SRTab").show();
    }
    else {
        $("#SRTab").hide();
    }

    var isChecked = ($('[name=effectiv_planning]:checked').length);

    if (isChecked == 1) {

        $("#PlanTab").show();
    }
    else {
        $("#PlanTab").hide();
    }

    var isChecked = ($('[name=effectiv_warehouse]:checked').length);

    if (isChecked == 1) {

        $("#WHTab").show();
    }
    else {
        $("#WHTab").hide();
    }
     
});

