$(function () {
    var trInstance = $('.SimpleGrid').find('tr');

    trInstance.click(function () {
        trInstance.removeClass('selectRowCSS');
        trInstance.find('td:eq(0)').removeClass('selectTdCSS');

        var instance = $(this);
        instance.addClass('selectRowCSS');
        instance.find('td:eq(0)').addClass('selectTdCSS');
    });
});


$(function () {
    var trInstance = $('#parentMenu').find('span');

    trInstance.click(function () {
        trInstance.removeClass('selectRowCSS');
        trInstance.find('td:eq(0)').removeClass('selectTdCSS');

        var instance = $(this);
        instance.addClass('selectRowCSS');
        instance.find('td:eq(0)').addClass('selectTdCSS');
    });
});


$(function () {
    var trInstance = $('#ChildMenu').find('span');

    trInstance.click(function () {
        trInstance.removeClass('selectRowCSS');
        trInstance.find('td:eq(0)').removeClass('selectTdCSS');

        var instance = $(this);
        instance.addClass('selectRowCSS');
        instance.find('td:eq(0)').addClass('selectTdCSS');
    });
});