function GetCodeWithAjax(code_type){var result;if($('#sub_sbj_id_sel').length!=0){sub_sbj_id=$('#sub_sbj_id_sel').val();}else{sub_sbj_id=null;}
if(arguments.length==2){que_str_id=arguments[1];}else{que_str_id=null;}
$.ajax({url:'/ajax/getcode.ajax.php',type:'post',async:false,dataType:"json",data:{code_type:code_type,sub_sbj_id:sub_sbj_id,que_str_id:que_str_id},success:function(data){result=data;}});return result;}
function CreateScreenEditors(ace_mode,lan_lin_offset,code_type){if(arguments.length==4){var result=GetCodeWithAjax(code_type,arguments[3]);;}else{var result=GetCodeWithAjax(code_type)}
if(ace_mode=='html'){window.aceEditor=CreateEditor('question_code',1,result.code.html);window.aceEditor.session.setMode("ace/mode/html");window.CSSEditor=CreateEditor('question_css',1,result.code.css);window.CSSEditor.session.setMode("ace/mode/css");window.JSEditor=CreateEditor('question_js',1,result.code.js);window.JSEditor.session.setMode("ace/mode/javascript");}else{window.aceEditor=CreateEditor('question_code',lan_lin_offset,result.code);}
document.question_submit_request=false;}
function CreateEditor(theEditor,line_offset,source)
{ace.require("ace/ext/language_tools");var myeditor=ace.edit(theEditor);myeditor.renderer.$gutterLayer.update=function(config){var emptyAnno={className:""};var html=[];var i=config.firstRow;var lastRow=config.lastRow;var fold=this.session.getNextFoldLine(i);var foldStart=fold?fold.start.row:Infinity;var foldWidgets=this.$showFoldWidgets&&this.session.foldWidgets;var breakpoints=this.session.$breakpoints;var decorations=this.session.$decorations;var lastLineNumber=0;while(true){if(i>foldStart){i=fold.end.row+1;fold=this.session.getNextFoldLine(i,fold);foldStart=fold?fold.start.row:Infinity;}
if(i>lastRow)
break;var annotation=this.$annotations[i]||emptyAnno;html.push("<div class='ace_gutter-cell ",breakpoints[i]||"",decorations[i]||"",annotation.className,"' style='height:",this.session.getRowLength(i)*config.lineHeight,"px;'>",lastLineNumber=i+line_offset);if(foldWidgets){var c=foldWidgets[i];if(c==null)
c=foldWidgets[i]=this.session.getFoldWidget(i);if(c)
html.push("<span class='ace_fold-widget ",c,c=="start"&&i==foldStart&&i<fold.end.row?" closed":" open","'></span>");}
html.push("</div>");i++;}
$(this.element).html(html.join(""));$(this.element).attr("height",config.minHeight+"px");if(this.session.$useWrapMode)
lastLineNumber=this.session.getLength();var gutterWidth=(""+lastLineNumber).length*config.characterWidth;var padding=this.$padding||this.$computePadding();gutterWidth+=padding.left+padding.right;if(gutterWidth!==this.gutterWidth){this.gutterWidth=gutterWidth;this.element.style.width=Math.ceil(this.gutterWidth)+"px";this._emit("changeGutterWidth",gutterWidth);}};myeditor.setShowPrintMargin(false);if(arguments.length==4){ace_mode=arguments[3];}else{ace_mode=$('#ace_mode').val();}
myeditor.setTheme("ace/theme/textmate");myeditor.session.setMode("ace/mode/"+ace_mode);myeditor.SetSource=function(source){this.setValue(source);if(($('textarea[name="txt"]').length==0)&&(source.search("<NON EDITABLE FILE/>")>0)){this.setReadOnly(true);therange=this.find('<NON EDITABLE FILE/>');this.find('zutzut');this.session.addFold("",new Range(therange.start.row+0,0,therange.end.row+2,300));}};myeditor.$blockScrolling=Infinity;myeditor.SetSource(source);myeditor.setOptions({enableBasicAutocompletion:true,enableSnippets:true});beg=myeditor.find($('#beg_tag').val());if(beg!=undefined)
{myeditor.gotoLine(beg.start.row+2);}
document.getElementById(theEditor).style.fontSize='11px';return myeditor;}
function CreateReadOnlyEditor(que_str_id)
{var result=GetCodeWithAjax('result_report',que_str_id)
return CreateReadOnlyEditorWithCode(que_str_id,result.code);}
function CreateReadOnlyEditorWithCode(que_str_id,code){editor=ace.edit('editor_'+que_str_id+'_code');editor.setShowPrintMargin(false);editor.setTheme("ace/theme/textmate");editor.session.setMode("ace/mode/"+ace_mode);editor.$blockScrolling=Infinity;editor.setValue(code);editor.setReadOnly(true);editor.clearSelection();editor.renderer.setShowGutter(false);editor.getSession().setUseWorker(false);return editor;}
function CreateReportEditors()
{var myeditors=new Array();ace_mode=$('#ace_mode').val();for(var iter=0;iter<arguments.length;iter++){que_str_id=arguments[iter];editor_name='editor_'+que_str_id+'_code';myeditors[iter]=CreateReadOnlyEditorWithCode(que_str_id,$('#'+que_str_id+'_answer').val());document.getElementById(editor_name).style.fontSize='11px';height=Math.min((myeditors[iter].session.getLength()*14)+20,1150);$('#'+editor_name).css('height',height);myeditors[iter].resize(true);$('#editor_'+que_str_id+"_container").css('height',height);beg=myeditors[iter].find($('#beg_tag').val());end=myeditors[iter].find($('#end_tag').val());myeditors[iter].clearSelection();if((typeof beg!=='undefined')&&(typeof end!=='undefined'))
{var therange=new Range(beg.start.row,0,end.start.row+1,0);myeditors[iter].getSession().addMarker(therange,"ace_active-line","fullLine");}}
return myeditors;}
function ResizeCodeArea(expand)
{var height=parseInt($(window).height());switch(expand){case 1:height=200;break;case 2:ResizeQuestionArea(2);case 4:height-=550;break;case 5:reduce=parseInt($('#question').height())+350;height=Math.max(200,height-reduce);break
default:height-=200;}
$('pre.code_input').css('height',height);$('div.code').css('height',height);window.aceEditor.resize(true);if($('#ace_mode').val()=='html'){$('div.code.multi_code').css('height',height+30);window.JSEditor.resize(true)
window.CSSEditor.resize(true);$("#html_result").css('height',height);}}
function ConcatenateCodes(){result=window.aceEditor.getSession().getValue();if($('#ace_mode').val()=='html'){result=result+"<--JS--!>"+window.JSEditor.getSession().getValue()+"<--END_JS--!>";result=result+"<--CSS--!>"+window.CSSEditor.getSession().getValue()+"<--END_CSS--!>";}
return result;}
function ShowCode(zone,me)
{$('div.code').each(function(){$(this).hide();});$('div.code.multi_code').each(function(){$(this).show();});$('p.onglet').each(function(){$(this).removeClass("selected");});$('#'+zone).show();$(me).parent().addClass("selected");}