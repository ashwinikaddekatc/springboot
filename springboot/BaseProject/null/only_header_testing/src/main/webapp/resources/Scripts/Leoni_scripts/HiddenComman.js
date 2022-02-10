function start_query(target) {
  

    $('#' + target).append('<div id="ajaxBusy"><p><img src="../../Content/images/load.gif" align="center" ></p></div>');

    $('#ajaxBusy').css({
        position: "none",
        margin: "0px",
        paddingLeft: "0px",
        paddingRight: "0px",
        paddingTop: "0px",
        paddingBottom: "0px",
        position: "absolute",
        right: "430px",
        top: "200px",
        width: "auto"
        //             opacity:0.4;
        //filter:alpha(opacity=40);
    });
    $(document).ajaxStart(function () {
        $('#ajaxBusy').show();
        $('*', 'form').css('opacity', '0.9').attr('background-color', 'gray');
        $('*', 'form').attr('disabled', 'disabled');
    }).ajaxStop(function () {
       
        $('#ajaxBusy').hide();

        $("#itemToFind").removeAttr('disabled', 'disabled');
        $('*', 'form').removeAttr('disabled', 'disabled');
        $('*', 'form').css('opacity', '').attr('background-color', '')
        $('*', 'form').css('opacity', '');
    });

}
