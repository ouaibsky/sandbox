function SubSbjIDHasChanged()
{$('#sub_sbj_id').val($('#sub_sbj_id_sel').val());$('#QuestionForm').attr("action","runtest.php").submit();}
function ContestSubmit(){QuestionSubmit();}
function QuestionSubmit(){ShowWaitArea(true);$('#final_code').val(ConcatenateCodes());if(document.count_submit<5)
{document.isogradajax=$.ajax({url:'ajax/questionsubmit.ajax.php',type:'post',data:$('#QuestionForm').serialize()+"&special=0",async:true,dataType:"json",success:function(data){var result=data;if((typeof result==='object')&&result.hasOwnProperty('next_screen')&&(result.next_screen.length>3||result.next_screen=="#")){ProcessResult(result);if(result.change_page){$('#QuestionForm').attr("action",result.next_screen);if(result.show_ranking){$('#ranking').css('display','none');OpenDialog("contest_success",{width:550,closeOnEscape:false});$(".ui-dialog-titlebar-close").css("display","none");}else{ContinueContest();}}}else{document.count_submit++;setTimeout(function(){ContestSubmit();},1000);}}});}else{alert(document.strings_runtest[2]);$('#QuestionForm').attr("action","/candidats/choosetest.php").submit();}}
function ContinueContest(){document.unload_is_ok=true;$(window).off('beforeunload');$('#QuestionForm').submit();}
function BackToList(return_page){document.unload_is_ok=true;$(window).off('beforeunload');}
function GetRankingText(result){var ranking_text;if(result.ranking==-1){return"";}
if(result.ranking==-2){return document.strings_runtest[11];}
else if(result.ranking==1){return document.strings_runtest[8];}
else if(result.ranking==2){return $.vsprintf(document.strings_runtest[9],[result.delay]);}else{return $.vsprintf(document.strings_runtest[10],[result.ranking,result.delay]);}}