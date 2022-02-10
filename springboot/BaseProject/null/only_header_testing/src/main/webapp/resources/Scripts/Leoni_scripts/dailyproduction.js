

/////////////////////////////////////////// No validation for job no /////////////////////////////////////// 
 function checkGridNumJobno(rowCount) {
 
        var num = $('.saveJobNo3' + rowCount).val();

        //check if valid number
        if (isNaN(num)) {

            $('.saveJobNo3' + rowCount).val('');
            $('.saveJobNo3' + rowCount).attr('placeholder', 'Enter Valid Number');
            $('.saveJobNo3' + rowCount).focus();


            $('.saveJobNo3' + rowCount).css('border-color', 'red');
            $('.saveJobNo3' + rowCount).css('background-color', '#FFE8A6');
        }
        else {

            $('.saveJobNo3' + rowCount).attr('placeholder', '');
            $('.saveJobNo3' + rowCount).focus();

            $('.saveJobNo3' + rowCount).css('border-color', '');
            $('.saveJobNo3' + rowCount).css('background-color', '');

        }

    }

    
/////////////////////////////////////////// No validation for ToRtv ///////////////////////////////////////
    function checkGridNumRtv(rowCount) {

        var num = $('.saveToRTV' + rowCount).val();

        //check if valid number
        if (isNaN(num)) {

            $('.saveToRTV' + rowCount).val('');
            $('.saveToRTV' + rowCount).attr('placeholder', 'Enter Valid Number');
            $('.saveToRTV' + rowCount).focus();


            $('.saveToRTV' + rowCount).css('border-color', 'red');
            $('.saveToRTV' + rowCount).css('background-color', '#FFE8A6');
        }
        else {

            $('.saveToRTV' + rowCount).attr('placeholder', '');
            $('.saveToRTV' + rowCount).focus();

            $('.saveToRTV' + rowCount).css('border-color', '');
            $('.saveToRTV' + rowCount).css('background-color', '');

        }

    }

        
/////////////////////////////////////////// No validation for Scraped ///////////////////////////////////////

    function checkGridNumscr(rowCount) {

        var num = $('.saveScraped' + rowCount).val();

        //check if valid number
        if (isNaN(num)) {

            $('.saveScraped' + rowCount).val('');
            $('.saveScraped' + rowCount).attr('placeholder', 'Enter Valid Number');
            $('.saveScraped' + rowCount).focus();


            $('.saveScraped' + rowCount).css('border-color', 'red');
            $('.saveScraped' + rowCount).css('background-color', '#FFE8A6');
        }
        else {

            $('.saveScraped' + rowCount).attr('placeholder', '');
            $('.saveScraped' + rowCount).focus();

            $('.saveScraped' + rowCount).css('border-color', '');
            $('.saveScraped' + rowCount).css('background-color', '');

        }

    }
            
/////////////////////////////////////////// No validation for Rejected ///////////////////////////////////////

    function checkGridNumRej(rowCount) {

        var num = $('.saveRejected' + rowCount).val();

        //check if valid number
        if (isNaN(num)) {

            $('.saveRejected' + rowCount).val('');
            $('.saveRejected' + rowCount).attr('placeholder', 'Enter Valid Number');
            $('.saveRejected' + rowCount).focus();


            $('.saveRejected' + rowCount).css('border-color', 'red');
            $('.saveRejected' + rowCount).css('background-color', '#FFE8A6');
        }
        else {

            $('.saveRejected' + rowCount).attr('placeholder', '');
            $('.saveRejected' + rowCount).focus();

            $('.saveRejected' + rowCount).css('border-color', '');
            $('.saveRejected' + rowCount).css('background-color', '');

        }

    }

    /////////////////////////////////////////// No validation for Passed ///////////////////////////////////////
    function checkGridNumPass(rowCount) {

        var num = $('.savepassed' + rowCount).val();

        //check if valid number
        if (isNaN(num)) {

            $('.savepassed' + rowCount).val('');
            $('.savepassed' + rowCount).attr('placeholder', 'Enter Valid Number');
            $('.savepassed' + rowCount).focus();


            $('.savepassed' + rowCount).css('border-color', 'red');
            $('.savepassed' + rowCount).css('background-color', '#FFE8A6');
        }
        else {

            $('.savepassed' + rowCount).attr('placeholder', '');
            $('.savepassed' + rowCount).focus();

            $('.savepassed' + rowCount).css('border-color', '');
            $('.savepassed' + rowCount).css('background-color', '');

        }

    }

        /////////////////////////////////////////// No validation for WIP  ///////////////////////////////////////
    function checkGridNumWip(rowCount) {

        var num = $('.saveWIP' + rowCount).val();

        //check if valid number
        if (isNaN(num)) {

            $('.saveWIP' + rowCount).val('');
            $('.saveWIP' + rowCount).attr('placeholder', 'Enter Valid Number');
            $('.saveWIP' + rowCount).focus();


            $('.saveWIP' + rowCount).css('border-color', 'red');
            $('.saveWIP' + rowCount).css('background-color', '#FFE8A6');
        }
        else {

            $('.saveWIP' + rowCount).attr('placeholder', '');
            $('.saveWIP' + rowCount).focus();

            $('.saveWIP' + rowCount).css('border-color', '');
            $('.saveWIP' + rowCount).css('background-color', '');

        }

    }
      /////////////////////////////////////////// No validation for WIP  ///////////////////////////////////////

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

    /////////////////////////////////////////////// WIP   //////////////////////////////////////////
function changeByWip(cnt) {

            checkGridNumWip(cnt);
         

            var queued = $(".saveQueued" + cnt).val();
           
            var wip = $(".saveWIP" + cnt).val();

            var pass = $(".savepassed" + cnt).val();

            var hidden = $(".hiddenqty" + cnt).val();


            var rejected = $(".saveRejected" + cnt).val();

            var scraped = $(".saveScraped" + cnt).val();

            var tortv = $(".saveToRTV" + cnt).val();



            var hiddenque = $(".hiddenque" + cnt).val();
            var hiddenwip = $(".hiddenwip" + cnt).val();
            var hiddenpass = $(".hiddenpass" + cnt).val();
            var hiddenreject = $(".hiddenreject" + cnt).val();
            var hiddenscrap = $(".hiddenscrap" + cnt).val();
            var hiddentortv = $(".hiddentortv" + cnt).val();

            if (parseInt(wip) <= parseInt(queued)) {



                if (parseInt(pass) != null) {
               
                    var temp = parseInt(wip) + parseInt(pass) + parseInt(rejected) + parseInt(scraped) + parseInt(tortv);

                    queued = parseInt(hidden) - parseInt(temp);

                    $(".saveQueued" + cnt).val(queued);
                    $(".saveWIP" + cnt).val(wip);
                    $(".savepassed" + cnt).val(pass);

                }
                else {
                
                    queued = parseInt(hidden) - parseInt(wip) - parseInt(rejected) + parseInt(scraped) + parseInt(tortv);

                    $(".saveQueued" + cnt).val(queued);
                    $(".saveWIP" + cnt).val(wip);
                    $(".savepassed" + cnt).val(pass);
                }
            }
            else if (parseInt(wip) >= parseInt(queued)) {

                var temp = parseInt(wip) + parseInt(pass) + parseInt(rejected) + parseInt(scraped) + parseInt(tortv);

                queued = parseInt(hidden) - parseInt(temp) - parseInt(rejected) - parseInt(scraped) - parseInt(tortv);
                if (queued >= 0) {
                    $(".saveQueued" + cnt).val(queued);
                    $(".saveWIP" + cnt).val(wip);
                    $(".savepassed" + cnt).val(pass);
                }
                else {

                    msg_box("Please insert the valid value for WIP!", 'A', function (return_val) {
                  

                        $(".saveQueued" + cnt).val(hiddenque);
                        $(".saveWIP" + cnt).val(hiddenwip);
                        $(".savepassed" + cnt).val(hiddenpass);

                        $(".saveRejected" + cnt).val(hiddenreject);
                        $(".saveScraped" + cnt).val(hiddenscrap);
                        $(".saveToRTV" + cnt).val(hiddentortv);
                    });
                 
                  
                }

            }

            else {
             
                msg_box("Please insert the valid value for WIP!", 'A', function (return_val) {  });
                $(".saveQueued" + cnt).val(hiddenque);
                $(".saveWIP" + cnt).val(hiddenwip);
                $(".savepassed" + cnt).val(hiddenpass);

                $(".saveRejected" + cnt).val(hiddenreject);
                $(".saveScraped" + cnt).val(hiddenscrap);
                $(".saveToRTV" + cnt).val(hiddentortv);
            }
        }

        /////////////////////////////////////////////// PASS //////////////////////////////////////////
function changeByPassed(cnt) {

        checkGridNumPass(cnt);

           
             var queued = $(".saveQueued" + cnt).val();

             var wip = $(".saveWIP" + cnt).val();

             var pass = $(".savepassed" + cnt).val();

             var hidden = $(".hiddenqty" + cnt).val();


             var rejected = $(".saveRejected" + cnt).val();

             var scraped = $(".saveScraped" + cnt).val();

             var tortv = $(".saveToRTV" + cnt).val();

             var hiddenque = $(".hiddenque" + cnt).val();
             var hiddenwip = $(".hiddenwip" + cnt).val();
             var hiddenpass = $(".hiddenpass" + cnt).val();
             var hiddenreject = $(".hiddenreject" + cnt).val();
             var hiddenscrap = $(".hiddenscrap" + cnt).val();
             var hiddentortv = $(".hiddentortv" + cnt).val();

             if (parseInt(pass) <= parseInt(wip)) {
                 if (parseInt(queued) != null) {

                     var temp = parseInt(queued) + parseInt(pass) + parseInt(rejected) + parseInt(scraped) + parseInt(tortv);

                     wip = parseInt(hidden) - parseInt(temp);

                     $(".saveQueued" + cnt).val(queued);
                     $(".saveWIP" + cnt).val(wip);
                     $(".savepassed" + cnt).val(pass);

                 }
                 else {

                     wip = parseInt(wip) - parseInt(pass);
                     var temp = parseInt(wip) + parseInt(pass) + parseInt(rejected) + parseInt(scraped) + parseInt(tortv);
                     queued = parseInt(hidden) - parseInt(temp);

                     $(".saveQueued" + cnt).val(queued);
                     $(".saveWIP" + cnt).val(wip);
                     $(".savepassed" + cnt).val(pass);
                 }
             }
             else if (parseInt(pass) >= parseInt(wip)) {

                 temp = parseInt(queued) + parseInt(pass) + parseInt(rejected) + parseInt(scraped) + parseInt(tortv);

                 if (parseInt(temp) <= parseInt(hidden)) {

                     wip = parseInt(hidden) - parseInt(temp);
                     $(".saveQueued" + cnt).val(queued);
                     $(".saveWIP" + cnt).val(wip);
                     $(".savepassed" + cnt).val(pass);
                 }
                 else {

                     queued = parseInt(hidden) - parseInt(pass) - parseInt(rejected) - parseInt(scraped) - parseInt(tortv);
                     wip = 0;
                     if (queued >= 0) {

                         $(".saveQueued" + cnt).val(queued);
                         $(".saveWIP" + cnt).val(wip);
                         $(".savepassed" + cnt).val(pass);
                     }
                     else {
                       
                         msg_box("Please insert the valid value for PASS!", 'A', function (return_val) { });
                         $(".saveQueued" + cnt).val(hiddenque);
                         $(".saveWIP" + cnt).val(hiddenwip);
                         $(".savepassed" + cnt).val(hiddenpass);

                         $(".saveRejected" + cnt).val(hiddenreject);
                         $(".saveScraped" + cnt).val(hiddenscrap);
                         $(".saveToRTV" + cnt).val(hiddentortv);
                     }
                 }

             }


         }

/////////////////////////////////////////////// REJECTED //////////////////////////////////////////

 function changeByRejected(cnt) {
  checkGridNumRej(cnt);

                
                  var queued = $(".saveQueued" + cnt).val();

                  var wip = $(".saveWIP" + cnt).val();

                  var pass = $(".savepassed" + cnt).val();

                  var hidden = $(".hiddenqty" + cnt).val();

                  var reject = $(".saveRejected" + cnt).val();

                  var scraped = $(".saveScraped" + cnt).val();

                  var tortv = $(".saveToRTV" + cnt).val();

                  var hiddenque = $(".hiddenque" + cnt).val();
                  var hiddenwip = $(".hiddenwip" + cnt).val();
                  var hiddenpass = $(".hiddenpass" + cnt).val();
                  var hiddenreject = $(".hiddenreject" + cnt).val();
                  var hiddenscrap = $(".hiddenscrap" + cnt).val();
                  var hiddentortv = $(".hiddentortv" + cnt).val();


                  if (parseInt(reject) <= parseInt(wip)) {

                      if (parseInt(queued) != 0) {

                          var temp = parseInt(queued) + parseInt(pass) + parseInt(reject) + parseInt(scraped) + parseInt(tortv);

                          wip = parseInt(hidden) - parseInt(temp);

                          $(".saveQueued" + cnt).val(queued);
                          $(".saveWIP" + cnt).val(wip);
                          $(".savepassed" + cnt).val(pass);
                      }
                      else {

                          wip = parseInt(wip) - parseInt(reject);
                          var temp = parseInt(wip) + parseInt(pass) + parseInt(reject) + parseInt(scraped) + parseInt(tortv);
                          queued = parseInt(hidden) - parseInt(temp);

                          $(".saveQueued" + cnt).val(queued);
                          $(".saveWIP" + cnt).val(wip);
                          $(".savepassed" + cnt).val(pass);
                      }

                  }


                  else if (parseInt(reject) >= parseInt(wip)) {

                      temp = parseInt(queued) + parseInt(pass) + parseInt(reject) + parseInt(scraped) + parseInt(tortv);

                      if (parseInt(temp) <= parseInt(hidden)) {

                          wip = parseInt(hidden) - parseInt(temp);
                          $(".saveQueued" + cnt).val(queued);
                          $(".saveWIP" + cnt).val(wip);
                          $(".savepassed" + cnt).val(pass);
                      }
                      else {

                          queued = parseInt(hidden) - parseInt(pass) - parseInt(reject) - parseInt(scraped) - parseInt(tortv);
                          if (queued >= 0) {
                              wip = 0;
                              $(".saveQueued" + cnt).val(queued);
                              $(".saveWIP" + cnt).val(wip);
                              $(".savepassed" + cnt).val(pass);
                          }
                          else {
                              msg_box("Please insert the valid value for REJECTED!", 'A', function (return_val) { });
                              $(".saveQueued" + cnt).val(hiddenque);
                              $(".saveWIP" + cnt).val(hiddenwip);
                              $(".savepassed" + cnt).val(hiddenpass);

                              $(".saveRejected" + cnt).val(hiddenreject);
                              $(".saveScraped" + cnt).val(hiddenscrap);
                              $(".saveToRTV" + cnt).val(hiddentortv);
                          }

                      }

                  }

              }

/////////////////////////////////////////////// SCRAPED  //////////////////////////////////////////


              function changeByScraped(cnt) {
  checkGridNumscr(cnt);

           
               var queued = $(".saveQueued" + cnt).val();

               var wip = $(".saveWIP" + cnt).val();

               var pass = $(".savepassed" + cnt).val();

               var hidden = $(".hiddenqty" + cnt).val();

               var reject = $(".saveRejected" + cnt).val();

               var scraped = $(".saveScraped" + cnt).val();

               var tortv = $(".saveToRTV" + cnt).val();

               var hiddenque = $(".hiddenque" + cnt).val();
               var hiddenwip = $(".hiddenwip" + cnt).val();
               var hiddenpass = $(".hiddenpass" + cnt).val();
               var hiddenreject = $(".hiddenreject" + cnt).val();
               var hiddenscrap = $(".hiddenscrap" + cnt).val();
               var hiddentortv = $(".hiddentortv" + cnt).val();

               if (parseInt(scraped) <= parseInt(wip)) {

                   if (parseInt(queued) != 0) {

                       var temp = parseInt(queued) + parseInt(pass) + parseInt(reject) + parseInt(scraped) + parseInt(tortv);

                       wip = parseInt(hidden) - parseInt(temp);

                       $(".saveQueued" + cnt).val(queued);
                       $(".saveWIP" + cnt).val(wip);
                       $(".savepassed" + cnt).val(pass);
                   }
                   else {

                       wip = parseInt(wip) - parseInt(scraped);
                       var temp = parseInt(wip) + parseInt(pass) + parseInt(reject) + parseInt(scraped) + parseInt(tortv);
                       queued = parseInt(hidden) - parseInt(temp);


                   }

               }


               else if (parseInt(scraped) >= parseInt(wip)) {

                   temp = parseInt(queued) + parseInt(pass) + parseInt(reject) + parseInt(scraped) + parseInt(tortv);

                   if (parseInt(temp) <= parseInt(hidden)) {

                       wip = parseInt(hidden) - parseInt(temp);
                       $(".saveQueued" + cnt).val(queued);
                       $(".saveWIP" + cnt).val(wip);
                       $(".savepassed" + cnt).val(pass);
                   }
                   else {

                       queued = parseInt(hidden) - parseInt(pass) - parseInt(reject) - parseInt(scraped) - parseInt(tortv);
                       if (queued >= 0) {
                           wip = 0;
                           $(".saveQueued" + cnt).val(queued);
                           $(".saveWIP" + cnt).val(wip);
                           $(".savepassed" + cnt).val(pass);

                       }
                       else {
                           msg_box("Please insert the valid value for SCRAP!", 'A', function (return_val) { });
                           $(".saveQueued" + cnt).val(hiddenque);
                           $(".saveWIP" + cnt).val(hiddenwip);
                           $(".savepassed" + cnt).val(hiddenpass);

                           $(".saveRejected" + cnt).val(hiddenreject);
                           $(".saveScraped" + cnt).val(hiddenscrap);
                           $(".saveToRTV" + cnt).val(hiddentortv);
                       }

                   }

               }

           }


/////////////////////////////////////////////// ToRtv  //////////////////////////////////////////

            function changeByToRtv(cnt) {
             checkGridNumscr(cnt);
                   
                     var queued = $(".saveQueued" + cnt).val();

                     var wip = $(".saveWIP" + cnt).val();

                     var pass = $(".savepassed" + cnt).val();

                     var hidden = $(".hiddenqty" + cnt).val();

                     var reject = $(".saveRejected" + cnt).val();

                     var scraped = $(".saveScraped" + cnt).val();

                     var tortv = $(".saveToRTV" + cnt).val();


                     var hiddenque = $(".hiddenque" + cnt).val();
                     var hiddenwip = $(".hiddenwip" + cnt).val();
                     var hiddenpass = $(".hiddenpass" + cnt).val();
                     var hiddenreject = $(".hiddenreject" + cnt).val();
                     var hiddenscrap = $(".hiddenscrap" + cnt).val();
                     var hiddentortv = $(".hiddentortv" + cnt).val();

                     if (parseInt(tortv) <= parseInt(wip)) {

                         if (parseInt(queued) != 0) {

                             var temp = parseInt(queued) + parseInt(pass) + parseInt(reject) + parseInt(scraped) + parseInt(tortv);

                             wip = parseInt(hidden) - parseInt(temp);

                             $(".saveQueued" + cnt).val(queued);
                             $(".saveWIP" + cnt).val(wip);
                             $(".savepassed" + cnt).val(pass);
                         }
                         else {

                             wip = parseInt(wip) - parseInt(tortv);
                             var temp = parseInt(wip) + parseInt(pass) + parseInt(reject) + parseInt(scraped) + parseInt(tortv);
                             queued = parseInt(hidden) - parseInt(temp);

                             $(".saveQueued" + cnt).val(queued);
                             $(".saveWIP" + cnt).val(wip);
                             $(".savepassed" + cnt).val(pass);
                         }

                     }


                     else if (parseInt(tortv) >= parseInt(wip)) {

                         temp = parseInt(queued) + parseInt(pass) + parseInt(reject) + parseInt(scraped) + parseInt(tortv);

                         if (parseInt(temp) <= parseInt(hidden)) {

                             wip = parseInt(hidden) - parseInt(temp);
                             $(".saveQueued" + cnt).val(queued);
                             $(".saveWIP" + cnt).val(wip);
                             $(".savepassed" + cnt).val(pass);
                         }
                         else {

                             queued = parseInt(hidden) - parseInt(pass) - parseInt(reject) - parseInt(scraped) - parseInt(tortv);

                             if (queued >= 0) {
                                 wip = 0;
                                 $(".saveQueued" + cnt).val(queued);
                                 $(".saveWIP" + cnt).val(wip);
                                 $(".savepassed" + cnt).val(pass);

                             }
                             else {
                                 msg_box("Please insert the valid value for TORTV!", 'A', function (return_val) { });
                                 $(".saveQueued" + cnt).val(hiddenque);
                                 $(".saveWIP" + cnt).val(hiddenwip);
                                 $(".savepassed" + cnt).val(hiddenpass);

                                 $(".saveRejected" + cnt).val(hiddenreject);
                                 $(".saveScraped" + cnt).val(hiddenscrap);
                                 $(".saveToRTV" + cnt).val(hiddentortv);
                             }

                         }

                     }

                 }
                 /////////////////////////////////////////////// Check MaterialQuantity  //////////////////////////////////////////
                 function CheckMaterialQty(cnt) {
             
                     var lastop = $(".lastop" + cnt).val();
                     var Job_no = $(".saveJobNo3" + cnt).val();
                     var OP = $("#saveOP").val();
                     var Message = "";
                     var Message1 = "";

                     if (OP!=lastop||OP==lastop) {

                         $.getJSON('/JobCard/CheckMaterial/', { Job_no: Job_no }, function (data) {

                             Message = data.Message;
                        
                             if (Message == "False") {

                                 var message = "Material is not available in stock you can not Proceed";
                                 $.messager.alert('Daily Production Report', message);
//                                 msg_box("Material is not available in stock you can not Proceed", 'A', function (return_val) { });
                             }
                             else if (Message == "True") {

                                 CheckMaterialIssue(cnt);
                             }

                             else if (Message == "True") {
                                 CheckQueued(cnt);
                             }

                         });

                     }
                 }



                 /////////////////////////////////////////////// Check MaterialQuantity  //////////////////////////////////////////

                 function CheckMaterialIssue(cnt) {

                    
                     var lastop = $(".lastop" + cnt).val();
                     var OP = $("#saveOP").val();
                     var Job_no = $(".saveJobNo3" + cnt).val();
                     var Message = "";
                  
                     if (OP != lastop || OP == lastop) {
                         $.getJSON('/JobCard/CheckMaterialIssueOrder/', { Job_no: Job_no, OP: OP }, function (data) {

                             Message = data.Message;
                             Component = data.Component;
                             if (Message == "False") {

                                 var message = Component+" " + "Material is Not Issued You Can Not Proceed";
                                 $.messager.alert('Daily Production Report', message);
                                 //                                 msg_box("Material is Not Issued You Can Not Proceed", 'A', function (return_val) { });
                             }
                             else {
                                 CheckQueued(cnt)
                             }

                         });

                     }
                 }


/////////////////////////////////////////////// Check Queued  //////////////////////////////////////////

                  function CheckQueued(cnt) {
                  
                      var Sup_ID_jobno = $(".saveJobNo3" + cnt).val();

                      var Sup_ID_op = $(".saveOP" + cnt).val();

                      $.getJSON('/JobCard/GetQueued/', { job_no: Sup_ID_jobno, op_code: Sup_ID_op }, function (data) {
                          //                          Dept = data.Dept;
                          Qty = data.Qty;
                          Wip = data.Wip;
                          Reject = data.Reject;
                          Pass = data.Passed;
                          Hidden = data.HiddenTotal;

                          HiddenQue = data.HiddenQue;
                          HiddenWip = data.HiddenWip;
                          HiddenPass = data.HiddenPass;
                          HiddenReject = data.HiddenReject;
                          HiddenScrap = data.HiddenScrap;
                          HiddenTortv = data.HiddenTortv;

                          Scrape = data.Scrape;
                          ToRtv = data.ToRtv;
                          getAssembly = data.getAssembly;

                          Type = data.Type;
                          Size = data.Size;
                          Color = data.Color;
                          Operation = data.Operation;

                          Operation_Type = data.Operation_Type;

                          var queued = $(".saveQueued" + cnt).val(Qty);
                          //                          var dept = $(".saveDepartment" + cnt).val(Dept);

                          var wip = $(".saveWIP" + cnt).val(Wip);
                          var pass = $(".savepassed" + cnt).val(Pass);
                          var reject = $(".saveRejected" + cnt).val(Reject);
                          var hide = $(".hiddenqty" + cnt).val(Hidden);
                          var scrap = $(".saveScraped" + cnt).val(Scrape);
                          var tortv = $(".saveToRTV" + cnt).val(ToRtv);

                          var type = $(".saveType" + cnt).val(Type);
                          var size = $(".saveSize" + cnt).val(Size);
                          var color = $(".saveColor" + cnt).val(Color);
                          var Operation = $(".saveRemark" + cnt).val(Operation);

                          var hiddenque = $(".hiddenque" + cnt).val(HiddenQue);
                          var hiddenwip = $(".hiddenwip" + cnt).val(HiddenWip);
                          var hiddenpass = $(".hiddenpass" + cnt).val(HiddenPass);
                          var hiddenreject = $(".hiddenreject" + cnt).val(HiddenReject);
                          var hiddenscrap = $(".hiddenscrap" + cnt).val(HiddenScrap);
                          var hiddentortv = $(".hiddentortv" + cnt).val(HiddenTortv);
                          $(".saveAssembly" + cnt).val(getAssembly);
                       
                          if (Operation_Type == "510") {
                           
                              $(".saveOPType" + cnt).prop('checked', false);
                          }
                          else {
                            
                              $(".saveOPType" + cnt).prop('checked', true);
                          }


                      });

    }


/////////////////////////////////////////////// Check MAchine  //////////////////////////////////////////

      function ResetContactType(rwcnt) {

          var type = $('.saveDepartment' + rwcnt).val();
        
//          if (type!= "COILING") {
//              $('.saveCheck' + rwcnt).show();
              $('#Save').hide();
              $('.saveQueued' + rwcnt).show();
              $('.saveWIP' + rwcnt).attr('readonly', 'readonly');
              $('.savepassed' + rwcnt).attr('readonly', 'readonly');
              $('.saveRejected' + rwcnt).attr('readonly', 'readonly');
              $('.saveScraped' + rwcnt).attr('readonly', 'readonly');
              $('.saveToRTV' + rwcnt).attr('readonly', 'readonly');

              $('.saveRemark' + rwcnt).attr('readonly', 'readonly');
              
         // }
//          if (type == "COILING") {
//              $('.saveCheck' + rwcnt).show();
              $('#Save').hide();
              $('.saveQueued' + rwcnt).show();
              $('.saveWIP' + rwcnt).attr('readonly', 'readonly');
              $('.savepassed' + rwcnt).attr('readonly', 'readonly');
              $('.saveRejected' + rwcnt).attr('readonly', 'readonly');
              $('.saveScraped' + rwcnt).attr('readonly', 'readonly');
              $('.saveToRTV' + rwcnt).attr('readonly', 'readonly');

              $('.saveRemark' + rwcnt).attr('readonly', 'readonly');
              
              
              
              
                  //  }
      
             if (type != null) {

                 $.getJSON("/JobCard/SetCookiesForMachine/", { type: type }, function (data) {

                 });
             }
            
             GetLineTypeValue(rwcnt);
         }

         /////////////////////////////////////////////// Check Operator  //////////////////////////////////////////
         function ResetOperator(rwcnt) {

             var machine = $('.saveMachine' + rwcnt).val();

             if (machine != "") {

                 $.getJSON("/JobCard/SetCookiesForOperator/", { machine: machine }, function (data) {

                 });
             }
             GetLineTypeValue(rwcnt);
         }

            /////////////////////////////////////////////// Check OP  //////////////////////////////////////////
         function getOPCode(rowcount) {
           
          checkGridNumJobno(rowcount);
          var Sup_ID = $(".saveJobNo3" + rowcount).val();
         
        
        var listItem = "<option>" + "" + "</option>";
        $.getJSON('/JobCard/GetOP/', { job_no: Sup_ID }, function (data) {
            $.each(data, function (i, txt) {

                listItem += "<option Value=" + txt.Text + ">" + txt.Text + "</option>";
                x = new Array(txt.Text);

            });


            for (var i = 1; i <= 10; i++) {

                $(".saveOP" + i).html(listItem);
            }
            lastop = (x[x.length - 1]);
            var l = $(".lastop" + rowcount).val(lastop);
            


            getAssembly(Sup_ID, rowcount);

        });
    }

      /////////////////////////////////////////////// Check Assembly  //////////////////////////////////////////
    function getAssembly(jobno, cnt) {
        //          alert(jobno);


        $.getJSON('/JobCard/GetAsseAndCustomer/', { job_no: jobno }, function (data) {


            getAssembly = data.getAssembly;
            getcustomer = data.getcustomer;


            $(".saveAssembly" + cnt).val(getAssembly);
            $(".saveCustomer" + cnt).val(getcustomer);

        });

    }

    /////////////////////////////////////////////// Show POP-up  //////////////////////////////////////////
 