function SetUpTimer(result)
{if(result.result_mode)
{local_count_down_action=true;}
else
{if(result.needs_global_timer)
{var callbackFunction=function(){$('#knob_globaltimer').val(100).trigger('change');SubmitQuestion(1);};if(document.is_code){callbackFunction=function(){$('#knob_globaltimer').val(100).trigger('change');document.confirm_submit=false;document.disconnect_request=1;if(!document.is_contest){SubmitQuestion(1);}
else{OpenDialog('contest_finish',{width:400});}};}
var layout=result.global_remaining_time<3600?'{mnn}:{snn} {desc}':'{hnn}:{mnn}:{snn} {desc}';var d=new Date();var n=d.getTime()/ 1000;document.global_timer_start_time=n-(parseInt(result.tst_tim)-parseInt(result.global_remaining_time));document.global_timer_end_time=document.global_timer_start_time+parseInt(result.tst_tim);if(document.is_code){thesize=100;}else{thesize=80;}
$('#knob_globaltimer').knob({min:0,max:100,displayInput:false,width:thesize,height:thesize,thickness:0.20,fgColor:'#eb5f69',bgColor:'#e3e0d9'});$('#globaltimer').countdown({until:result.global_remaining_time,compact:true,format:'HMS',description:'',layout:layout,onExpiry:callbackFunction,onTick:function(){UpdateKnob("knob_globaltimer",document.global_timer_start_time,document.global_timer_end_time);}});local_count_down_action=true;if(result.global_remaining_time<-3)
{SubmitQuestion(1);}}
local_count_down_action=false;}
if(result.needs_local_timer&&!document.is_remote){CreateLocalTimer(result.que_tim,result.local_remaining_time,local_count_down_action);}else{document.question_time=parseInt(result.que_tim);document.local_remaining_time=parseInt(result.local_remaining_time);}}
function CreateLocalTimer(que_tim,remaining_time,isactive)
{if(remaining_time>0){var d=new Date();var n=d.getTime()/ 1000;document.local_timer_start_time=n-(parseInt(que_tim)-parseInt(remaining_time));document.local_timer_end_time=document.local_timer_start_time+parseInt(que_tim);if(document.is_code){var thesize=120;}else{var thesize=70;}
$('#knob_thetimer').knob({min:0,max:100,displayInput:false,width:thesize,height:thesize,thickness:0.24,fgColor:'#eb5f69',bgColor:'#e3e0d9'});$('#thetimer').countdown({until:remaining_time,compact:true,format:'HMS',description:'',layout:' {mnn}:{snn} {desc}',onExpiry:function(){document.isogradajax=false;$('#knob_thetimer').val(100).trigger('change');TimeOver(isactive);},onTick:function(){UpdateKnob("knob_thetimer",document.local_timer_start_time,document.local_timer_end_time);}});}}
function TimeOver(isactive)
{document.local_timeover=true;OpenDialog("timeover",{width:420,closeOnEscape:false});$('#timeover').parent().find('span.ui-dialog-title').css("font-size:","18px");$('#timeover').parent().find('span.ui-dialog-title').css("padding-left","70px");$('#timeover').parent().find('span.ui-dialog-title').css("text-align","left");$('#timeover').parent().find('a.ui-dialog-titlebar-close').hide();if(document.is_remote&&!document.is_debug){mythinrdp.sendKeyStroke(0x1B);window.setTimeout(function(){mythinrdp.sendKeyStroke(0x1B);},200);window.setTimeout(function(){CloseThinRDP();},400);}}
function UpdateKnob(knob_id,start_time,end_time){var d=new Date();var n=d.getTime()/ 1000;var timer_value=100*(n-start_time)/(end_time-start_time);$('#'+knob_id).val(timer_value).trigger('change');}