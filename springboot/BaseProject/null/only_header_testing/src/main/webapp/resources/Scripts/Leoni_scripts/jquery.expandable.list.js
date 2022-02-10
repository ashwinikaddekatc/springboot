// Generated by OMFYS Developers


function GetMenuGroup(id) {
    //$('#mainmenu').style('background-color','Red');
    //        document.getElementById('mainmenu').style.color = "Red";
    $.ajax({
        type: "GET",
        url: "GetMenuGroup",
        data: "&menuId=" + id,
        success: function (htmlPartialView) {
            $("#left_sidebar").html(htmlPartialView);

        },
        error: function (xhr, textStatus, errorThrown) {

        }
    });

}


(function() {
  $.fn.extend({
    expList: function(options) {
      var opts, self;

      self = $.fn.expList;
      opts = $.extend({}, self.default_options, options);
      return $(this).each(function(i, elem) {
        return self.init(elem, opts);
      });
    }
  });

  $.extend($.fn.expList, {
    default_options: {
      duration:500,
      enableLinks: true
    },
    init: function(elem, opts) {
      var child, childrenList, _fn, _i, _len;

      $(elem).find('ul').hide();
      childrenList = $(elem).find('li:has(ul)');
      _fn = function() {
        return $(child).click(function(e) {
          $(e.target).closest('li').children('ul').toggle(opts.duration);
          return e.stopPropagation();
        });
      };
      for (_i = 0, _len = childrenList.length; _i < _len; _i++) {
        child = childrenList[_i];
        _fn();
      }
      if (opts.enableLinks) {
        return $(elem).find('a').off('click').on('click', function(e) {
          return e.stopPropagation();
        });
      }
    }
  });

}).call(this);
