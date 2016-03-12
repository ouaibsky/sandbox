function CodeQuestionWindowLoad()
{CreateScreenEditors($('#ace_mode').val(),parseInt($('#lan_lin_off').val()),'displayed')
$(".lateral_helparea").bind("click",function(){ShowLateral();});if(parseInt($('#question_text').css("height"))<250){$('#medium_question').hide();}
ResizeQuestionArea(3);ResizeCodeArea(5);ShowCode('question_code_container','#show_code');document.is_contest=($('#sub_sbj_id').length!=0);document.count_submit=0;document.is_html=($('#ace_mode').val()=='html');document.strings_runtest_code=javaGetStrings("runtest_code.js","tst");if(document.is_html){HTMLQuestionWindowLoad();}}
function ShowSolution(){if($('#editor_src_1').html().length>3){CreateReadOnlyEditorWithCode(1,$('#editor_src_1').val());document.getElementById('editor_1_code').style.fontSize='10px';}
OpenDialog("solution",{width:760,height:600,resizable:true,closeOnEscape:true});$("#solution_code_container").show();}
function ShowLateral()
{$(".lateral_helparea").animate({width:"280px"},200,"linear");$(".lateral_helparea").unbind("click");$(".lateral_helparea").bind("click",function(){HideLateral();});}
function HideLateral()
{$(".lateral_helparea").animate({width:"3px"},200,"linear");$(".lateral_helparea").unbind("click");$(".lateral_helparea").bind("click",function(){ShowLateral();});}
function ShowWaitArea(include_stop_button)
{var speed=20;if(document.is_html){include_stop_button=false;}
if($('#ace_mode').val()=='java'){speed=150;}
$('.progress-bar').css('width','0%');$('#wait_area').removeClass('hide');$('.control-bar-buttons').addClass('hide');$('#stop_processing_button').toggle(include_stop_button);AnimateProgressBar(speed);}
function HideWaitArea()
{$('#wait_area').addClass('hide');$('.control-bar-buttons').removeClass('hide');clearInterval(timer_interval_wait);}
function TestAnswer()
{var result="";var thedata,cod,answer;ShowWaitArea(true);if(window.hasOwnProperty('aceEditor'))
{cod=ConcatenateCodes();}
else
{cod=null;}
$('#local_echo_display').hide();$('#local_echo').removeClass("open");thedata={final_code:cod,special:0,remote_processing:$('#remote_processing').val()};if($('#sub_sbj_id').length!=0){thedata['sub_sbj_id']=$('#sub_sbj_id').val();}
document.isogradajax=$.ajax({url:'ajax/tryanswer.ajax.php',type:'post',data:thedata,async:true,dataType:"json",success:function(data){ProcessResult(data);if(document.is_contest&&data.hasOwnProperty('score')&&(data.score==100)&&!(document.is_debug)){SubmitCodeAnswer();}
if(data.hasOwnProperty('score')&&(data.score==100)){if(!document.is_html){OpenDialog("correct_answer_information_dialog",{width:450,closeOnEscape:false});$('#correct_answer_information_dialog').parent().find('a.ui-dialog-titlebar-close').hide();}}},error:function(){answer=document.strings_runtest_code[6];$('#server_output').html("");$('#server_errors').html("");$('#local_echo').removeClass("open");$('#local_echo_display').html('').hide();HideWaitArea();$('#question_success').text(answer).show();}});}
function ProcessResult(result)
{var result_score;var answer;$('#local_echo').removeClass("open");$('#local_echo_display').html('').hide();if((typeof result==='object')&&result.hasOwnProperty('success'))
{if(result.success)
{$('#server_output').html(result.output);$('#server_errors').html(result.message);if(result.type=="HTML")
{DisplayHTMLResult(result);answer="";}
else
{answer=$.vsprintf(document.strings_runtest_code[1],[result.score,result.processing_time]);}
if((result.local_echo!="")&&($('#local_echo').length!=0))
{$('#local_echo').addClass("open");$('#local_echo_display').html(result.local_echo).show();}}
else
{answer=document.strings_runtest_code[6];$('#server_output').html("");$('#server_errors').html(result.message);}}
else
{answer=document.strings_runtest_code[6];$('#server_output').html("");$('#server_errors').html("");}
$('#question_success').text(answer).show();ToggleOutput("#output",'#server_output',$('#server_output').html()!="")
ToggleOutput("#errors",'#server_errors',$('#server_errors').html()!="")
if(document.hasOwnProperty('html_first_display')&&document.html_first_display){$('#question_success').hide();}
if(!document.question_submit_request){HideWaitArea();}
if(result.type!="HTML"){document.location.href="#question_success";}}
function StopProcessing()
{document.isogradajax.abort();$('#server_output').html('');$('#server_errors').html('');$('#html_result').html();$('#local_echo_display').html('');if($('#show_question_success_area').val()==1)
{$('#question_success').text("Execution has been aborted");$('#question_success').show();}
HideWaitArea();}
function SubmitCodeAnswer()
{var ok;if(!document.hasOwnProperty('isogradajax')&&!document.is_contest&&document.confirm_submit)
{ok=confirm(document.strings_runtest_code[8]);}
else
{ok=true;}
if(ok)
{if(document.is_html){document.question_submit_request=true;TestHTMLAnswer();return false;}
ShowWaitArea(false);$('#final_code').val(ConcatenateCodes());}
return ok;}
function HideCorrectAnswerDialogButton(){$('#correct_answer_information_dialog .button_area').addClass('hide');}
function ToggleOutput(object,zone,show)
{$(object).toggleClass("open",show);$(zone).toggle(show);}
function ToggleHint()
{$('#hint').toggle();}
function ResetCode()
{var thestrings=javaGetStrings("runtest_code.js","tst");var result;if(confirm(thestrings[15])){result=GetCodeWithAjax('reset')
if(document.is_html){ShowCode('code','#show_code');window.aceEditor.SetSource(result.code.html);window.aceEditor.resize(true);ShowCode('css','#show_css');window.CSSEditor.SetSource(result.code.css);window.CSSEditor.resize(true);ShowCode('js','#show_js');window.JSEditor.SetSource(result.code.js);window.JSEditor.resize(true);ShowCode('code','#show_code');}else{window.aceEditor.SetSource(result.code);}}}
function ResizeQuestionArea(size)
{var size,max_height;$('#question_text').removeAttr("height");if(size==1){if(document.is_contest){max_height='50px';}else{max_height="180px";}
$('#question_text').css("height",max_height);$('#question_text').css("max-height",max_height);$('#question_text').css("overflow-y","scroll");}
else{if(size==2){$('#question_text').css("height","auto");$('#question_text').css("max-height","250px");$('#question_text').css("overflow-y","scroll");ResizeCodeArea(4);}
else{$('#question_text').css("height","auto");$('#question_text').css("max-height","3000px");$('#question_text').css("overflow-y","visible");}}}