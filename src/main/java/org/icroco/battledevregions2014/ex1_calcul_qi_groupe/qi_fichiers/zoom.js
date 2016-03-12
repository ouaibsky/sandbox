function ZoomUpImageQuestion()
{$('#imagequestion').hide();$('#imagequestion img').removeClass("small");$('#zoomquestionbutton').unbind('click');$('#zoomquestionbutton').addClass("zoomin");$('#imagequestion').show();$('#zoomquestionbutton').click(function(){ZoomDownImageQuestion();});}
function ZoomDownImageQuestion()
{$('#imagequestion').hide();$('#imagequestion img').addClass("small");$('#zoomquestionbutton').unbind('click');$('#zoomquestionbutton').click(function(){ZoomUpImageQuestion();});$('#zoomquestionbutton').removeClass("zoomin");$('#imagequestion').show();}
function GetPlayerID(){if($('#html5_player').length!=0){return'#html5_player';}else{return'#player';}}
function ZoomDownVideoQuestion()
{var player=GetPlayerID();$(player).hide();factor=$('#original_hei').val()/ 300;theheight=Math.min($('#original_hei').val(),300);thewidth=$('#original_wid').val()/ Math.max(1,factor);$(player).width(parseInt(thewidth));$(player).height(parseInt(theheight));$('#zoomvideobutton').unbind('click');$('#zoomvideobutton').click(function(){ZoomUpVideoQuestion();});$('#zoomvideobutton').removeClass("zoomin");$(player).show();}
function ZoomUpVideoQuestion()
{var player=GetPlayerID();$(player).hide();$(player).width(parseInt($('#original_wid').val()));$(player).height(parseInt($('#original_hei').val()));$('#zoomvideobutton').unbind('click');$('#zoomvideobutton').click(function(){ZoomDownVideoQuestion();});$('#zoomvideobutton').addClass("zoomin");$(player).show();}
function myautosize(){var global_max_height=0;var maxwidth,maxheight;var HasResized=false;if(($('li.BoiteReponseMulti').length!=0))
{maxwidth=900;maxheight=150;}
else
{maxwidth=400;if($('#laptop').val()==1)
{maxheight=200;}
else
{maxheight=300;}}
$('ul.mcq_answer').find('li:eq(1) img').each(function(){var oldheight=parseInt($(this).get(0).naturalWidth);var oldwidth=parseInt($(this).get(0).naturalHeight);var newheight=$(this).height()*Math.min(1,maxwidth / $(this).width());var newwidth=Math.min(maxwidth,$(this).width());if(newheight>maxheight)
{newwidth=newwidth*maxheight / newheight;newheight=maxheight;}
$(this).width(Math.round(newwidth));$(this).height(Math.round(newheight));global_max_height=Math.max(global_max_height,newheight);if((newheight / oldheight<0.95)||(newwidth / oldwidth<0.95))
{HasResized=true;}
$(this).show();});$('ul.mcq_answer').css('height',global_max_height+40);$('#QuestionForm a.zoomreponse').toggle(HasResized);window.setTimeout(function(){$('#answers').show();},100);}