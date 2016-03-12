function formatTitle(title,currentArray,currentIndex,currentOpts){return'<div id="tiptitle">'+title+'</div>';};function createtips()
{$('div.admincontent a.loupe,div.admincontent a.smallloupe').each(function(index){var link=$(this);var imgPreloader=new Image();imgPreloader.onload=function(){var imgWidth=imgPreloader.width;imgWidth+=10;link.cluetip({activation:'click',arrows:true,cluetipClass:'icat',width:imgWidth,dropShadow:true,dropShadowSteps:3,closePosition:'top',showTitle:false});};imgPreloader.src=link.attr('rel');});$(document.documentElement).keyup(function(event){hidetiponesc(event);});}
function hidetiponesc(e)
{var key;if(window.event)
key=window.event.keyCode;else
key=e.which;if(key==27){$('#cluetip').hide();}
return(true);}
function SubmitQuestion(status)
{var special;document.unload_is_ok=true;document.submit_question_form=true;$(window).off('beforeunload');ShowWaitText('boutonvalidation',"");$('#boutondontknow').hide();$('#btnstop').hide();switch(status)
{case 1:if(document.is_code){if(!SubmitCodeAnswer(true)){return;}}
special=0;break;case 2:if(document.is_code){$('#final_code').val(ConcatenateCodes());}
special=1;$('#answers .control-bar-buttons').hide();break;case 3:special=2;break;case 4:special=0;break;case 5:special=5;break;case 6:special=6;break;case 7:special=7;break;case 8:special=8;break;case 9:if($('#change_question').val()==-1){document.submit_question_form=false;}
else{if(document.is_code){$('#final_code').val(ConcatenateCodes());}
special=9;$('<input>').attr({type:'hidden',name:'change_question',value:$('#change_question').val()}).appendTo('#QuestionForm');}
break;case 10:special=10;$('<input>').attr({type:'hidden',name:'error_message',value:document.connection_error_message}).appendTo('#QuestionForm');break;case 11:special=11;break;case 12:special=12;break;}
$('<input>').attr({type:'hidden',name:'special',value:special}).appendTo('#QuestionForm');$('<input>').attr({type:'hidden',name:'time_over',value:document.local_timeover}).appendTo('#QuestionForm');if(document.submit_question_form){document.count_submit=0;ContinueSubmit();}}
function ContinueSubmit()
{if(document.count_submit<5)
{$.ajax({url:'ajax/questionsubmit.ajax.php',type:'post',data:$('#QuestionForm').serialize(),async:(document.is_code&&($('#ace_mode').val()=='java')),dataType:"json",success:function(data){result=data;if((typeof result==='object')&&result.hasOwnProperty('next_screen')&&(result.next_screen.length>3)){if(document.is_remote){document.disconnect_request=1;if(mythinrdp!=null){mythinrdp.disconnect();}}
$('#QuestionForm').attr("action",result.next_screen).submit();}else{document.count_submit++;setTimeout(function(){ContinueSubmit(thedata);},1000);}}});}else{alert(document.strings_runtest[2]);$('#QuestionForm').attr("action","/candidats/choosetest.php").submit();}}
$(document).ready(function(){var result;document.strings_runtest=javaGetStrings("runtest.js","tst");$.ajax({url:'/ajax/getcorrectanswer.ajax.php',type:'post',async:false,dataType:"json",success:function(data){result=data;}});if(!(typeof result==='object')){alert(document.strings_runtest[2]);$('#QuestionForm').attr("action","/candidats/choosetest.php").submit();}
document.is_error_screen=($('#is_error_screen').length!=0);if(!document.is_error_screen){document.is_remote=result.is_remote;document.is_code=result.is_code;document.is_contest=result.is_contest;document.is_debug=result.debug;document.unload_is_ok=false;document.local_timeover=false;document.needs_local_timer=result.needs_local_timer;document.result_mode=result.result_mode;createtips();SetUpTimer(result);if(result.is_mcq){DisplayRightAnswers(result);if(result.is_multi)
{$("a[rel=zoom_reponse]").fancybox({'hideOnContentClick':false,'titlePosition':'inside','autoDimensions':true,'titleFormat':formatTitle});}}
if(result.result_mode&&(result.is_remote||result.is_mcq)){$("#answered_questions").dialog({width:250,resizable:false,closeOnEscape:false,position:{my:"left+175%"},close:function(event,ui){window.location=$('#submit_href').val();}});}
document.confirm_submit=document.is_code;}else{document.unload_is_ok==true;}
if(result.debug||result.result_mode||(result.pla_tst_id<0)||($('#is_error_screen').val()==1)){document.unload_is_ok=true;}
if(document.unload_is_ok==false){$(window).on('beforeunload',function(){return(document.unload_is_ok==false)?document.strings_runtest[1]:'';});}
document.submit_question_form=false;});$(window).load(function(){if(document.is_remote&!document.result_mode){DisplayThinRDP();}
$("#question").find('#zoomquestionbutton').click(function(){ZoomUpImageQuestion();});$("#question").find('#zoomvideobutton').click(function(){ZoomUpVideoQuestion();});$("#cluetip").click(function(event){$('#cluetip').hide();});if(($('li.BoiteReponseMulti').length!=0)||($('li.BoiteReponseMultiV').length!=0))
{myautosize();}
if(!($('div#drag_column').length==0))
{DocumentReadyDragAndDrop();}
if(!($('div#source_column').length==0))
{DocumentReadyLinkAnswer();}
if(!($('img#click_in_area_image').length==0))
{DocumentReadyClickInArea();}
if(!($('div#click_in_text_area').length==0))
{DocumentReadyClickInText();}
if(document.is_code){CodeQuestionWindowLoad();}
if(!($('#player').length==0))
{var nb_resume=0;var theplaylist=($('#spl_fil_nam').length!=0)?[{url:$('#spl_fil_nam').val()}]:[];theplaylist.push({url:$('#img_fil').val(),autoPlay:false,autoBuffering:true});flowplayer("player",{src:$('#flowplayer_path').val(),wmode:'opaque',bgcolor:"#ffffff"},{key:'#$c1314eabd8120fb2d96',logo:{opacity:0},plugins:{controls:{volume:false,mute:false,fullscreen:false,backgroundColor:"#717E8B"}},playlist:theplaylist,play:{url:$("#url_play_button").val(),width:96,height:96},onFinish:function(){if(nb_resume>=2){nb_resume=99;this.pause();}},onResume:function(){if(nb_resume==99){nb_resume=1;this.play();}
nb_resume++;}});}});function TestNonCodeAnswer()
{if($('#can_ans').val()!=""){document.isogradajax=$.ajax({url:'ajax/tryanswer.ajax.php',type:'post',data:{can_ans:$('#can_ans').val(),special:0},async:true,dataType:"json",success:function(data){if(data.hasOwnProperty('score')){$('#try_success').toggle(data.score!=0);$('#try_error').toggle(data.score==0);$('#cor_ans_dia_clo_btn').parent().toggle(data.score==0);$('#cor_ans_dia_sub_btn').parent().toggle(data.score!=0);OpenDialog("correct_answer_information_dialog",{width:450,closeOnEscape:true});}}});}}