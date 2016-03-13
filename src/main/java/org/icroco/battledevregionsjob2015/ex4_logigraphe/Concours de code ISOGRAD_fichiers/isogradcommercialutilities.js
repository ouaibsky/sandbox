// JavaScript Document



/**************BROWSER DETECTION ***********************/
var matched, browser;

// Use of jQuery.browser is frowned upon.
// More details: http://api.jquery.com/jQuery.browser
// jQuery.uaMatch maintained for back-compat
jQuery.uaMatch = function( ua ) {
    ua = ua.toLowerCase();

    var match = /(chrome)[ \/]([\w.]+)/.exec( ua ) ||
        /(webkit)[ \/]([\w.]+)/.exec( ua ) ||
        /(opera)(?:.*version|)[ \/]([\w.]+)/.exec( ua ) ||
        /(msie) ([\w.]+)/.exec( ua ) ||
        ua.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec( ua ) ||
        [];

    return {
        browser: match[ 1 ] || "",
        version: match[ 2 ] || "0"
    };
};

matched = jQuery.uaMatch( navigator.userAgent );
browser = {};

if ( matched.browser ) {
    browser[ matched.browser ] = true;
    browser.version = matched.version;
}

// Chrome is Webkit, but Webkit is also Safari.
if ( browser.chrome ) {
    browser.webkit = true;
} else if ( browser.webkit ) {
    browser.safari = true;
}

if (!!navigator.userAgent.match(/Trident\/7\./)){
	browser.msie=true;
	browser.mozilla = false;
	browser.version=11;
}

if (!!navigator.userAgent.match(/Trident\/6\./)){
	browser.msie=true;
	browser.mozilla = false;
	browser.version=10;
}

jQuery.browser = browser;


/*************************************/

function GenericDocumentReady()
{
	if ($.browser.msie)
	{
		var userAgent = new String($.browser.version);
		if ((userAgent.charAt(0) == "6") || (userAgent.charAt(0) == "7") || (userAgent.charAt(0) == "8"))
		{
			document.location.href = 'https://www.isograd.com/ie9required.php';
		}
	}

	$("section#TakeATest input[name=ema]").keypress(function(event)
	{
		if (event.which == 13)
		{
			var lan_id = $(this).attr('data-lan-id');
			event.preventDefault();
			SubmitAsideEmail(false, lan_id);
		}

	});


	$("section#CreateAccount input[name=ema_corporates]").keypress(function(event)
	{
		if (event.which == 13)
		{
			var lan_id = $(this).attr('data-lan-id');
			event.preventDefault();
			SubmitAsideEmail(true, lan_id);
		}
	});
}

function ChangeUrl(url){
        document.location = url;
}
/***********************************************************************
****                   Create acccount related						****
/***********************************************************************/
function SocialInit(){
	$('a.facebook').hide();	
	FacebookInit();
	LinkedInInit();
}

function ContinueLoginIndividuals(fst_nam,lst_nam,ema,gender)
{	$('#fst_nam').val(fst_nam);
	$('#lst_nam').val(lst_nam);
	$('#ema').val(ema);
	$('#gender').val(gender);
	document.submit_is_ok=true;
	$('#formregister').submit();
}

function SubmitAsideEmail(is_admin, lan_id)
{	var result;
        var ema;
        var admin;

	if(is_admin) {
		ema = $('#ema_corporates').val();
		admin = 1;
		dialog_id = "#account_alert_dialog";
		$('#submit_corporates').hide();
	}
	else {
		ema = $('#aside_pos_ema').val();
		admin = 0;
		dialog_id = "#positioning_alert_dialog";
		$('#submit_individuals').hide();
	}
	$.ajax(
	{	url:'/ajax/sendregisterconfirmemail.ajax.php',
		type:'post', 
		data:{ema:ema, is_admin:admin, lan_id:lan_id},
		dataType:'json',
		async:false,
		success :function (data){result=data;}
	});	
	$(dialog_id+' p.message_content').toggleClass('error',!result.success).html(result.message);
	$(dialog_id).dialog({modal:true,width:650, resizable:false});
	$('span.ui-dialog-title').addClass('success',result.success);
	$('#submit_individuals').show();
	$('#submit_corporates').show();
	$('#register_dialog').dialog("destroy");
}

/***********************************************************************
****             FACEBOOK RELATED									****
/***********************************************************************/
function FacebookInit()
{	$.ajaxSetup({ cache: true });
  	$.getScript('//connect.facebook.net/en_UK/all.js', function(){
		window.fbAsyncInit = function() {
			FB.init({
				appId: '569902936519060',
				channelUrl: '//commercial.isograd.com/channel.html',
			}); 
			FB.getLoginStatus(function(response) {
				 if (response.status === 'connected') {
					  $('a.facebook').click(function(){FBGetDetails(response);});
				 } 
				 else if (response.status === 'not_authorized') {
					  $('a.facebook').click(function(){FB.login(function(response){ FBGetDetails(response);},{scope:'email'})});
						
				} 
				else {
					 // the user isn't logged in to Facebook.
					  $('a.facebook').click(function(){FB.login(function(response){ FBGetDetails(response);},{scope:'email'})});
				}
				$('a.facebook').show();
			});      
		};
	});
}

function FBGetDetails(response)
{	if (response.authResponse) {
		FB.api('/'+response.authResponse.userID+'?fields=id,name,email,age_range,first_name,last_name,gender', function(response) {
			 ContinueLoginIndividuals(response.first_name,response.last_name,response.email,response.gender);});
	}
}

/***********************************************************************
****           LINKEDIN RELATED										****
/***********************************************************************/
function LinkedInInit()
{
	$('a.linkedin').click(function () {IN.User.authorize(); return false;});
	IN.Event.on(IN, 'auth', function(){
		IN.API.Profile("me").fields(["id,firstName,lastName,positions,emailAddress"]).result(function(res){
		ContinueLoginIndividuals( res.values[0].firstName,res.values[0].lastName,res.values[0].emailAddress,"");
		});
	});
}

/***********************************************************************
****                   CheckMandatoryFields						****
/***********************************************************************/
function CheckMandatoryFields(fields,theform)
{	var result= {success:true,message:"",use_fields_for_error:true};
	var	first_error_field = '';
	
	$('#'+theform+' form').each(function(){HideErrorFieldMessage(this)});
	for(iter=0;iter<fields.length;iter++)
	{	if (($('#'+fields[iter]).length>0) && ($('#'+fields[iter]).val().length<3))
		{	DisplayErrorFieldMessage('#'+fields[iter]);
			if (first_error_field=='') {
				first_error_field = '#'+fields[iter];
			}
			result.success=false;
		}
	}
	if (!result.success) {
		$('html, body').animate({
         scrollTop: $(first_error_field).offset().top
     }, 1000);
	}
	return result;
}

/* Ajouter une fonction anonym en paramètre facultatif*/
function CheckValueFields(fctShowErrorField){
    if( typeof(fctShowErrorField) == 'undefined' ){
        fctShowErrorField = DisplayError;
    }
    
	document.fields_are_ok = true;
	$.each($('.field'), function( key, value ) {
            if(!CheckValueField($(this), fctShowErrorField)){
               document.fields_are_ok = false;
            }
    });
	return document.fields_are_ok ;
}

function CheckValueField($field, fctShowErrorField){
     
    var required = $field.attr('data-required');
    var type = $field.attr('data-type');
    var value = $field.val();
    var result = true;
    var doNoThing =false;
    
    //If the field is required but empty
    if(required == '1' && $.trim(value) == ''){
        result = false;
    }
    //Else we check its value
    else{
        switch(type)
        {
            case 'nam':
                for(var i=0; i<value.length; i++){
                    if(!isNaN(value.charAt(i)) && $.trim(value.charAt(i)) != ''){
                        result = false;
                    }
                }
              break;
            case 'email':
                var reg = new RegExp('^[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*@[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*[\.]{1}[a-z]{2,6}$', 'i');

                if(!reg.test(value))
                {
                       result = false;;
                }

              break;
            case 'phone':
                for(var i=0; i<value.length; i++){
                    if(isNaN(value.charAt(i))){
                        result = false;
                    }
                }
                break;
            case 'select':
                if(value == -1){
                    result = false;
                }
                break;
             break;

            default:

        }
    }
    
	/*Si une fonction anonyme a été passée en paramètre, l'appeler en lui indiquant si elle doit ajouter ou supprimer l'erreur*/
	/*Si aucune fonction n'a été passée, appeler DisplayErrorFieldMessage('#field') ou HideErrorFieldMessage('#field'); */
    
    if(!result){
        if( typeof(fctShowErrorField) == 'undefined' ){
            DisplayError($field.attr('id'), 'error', true, -1);
         }
         else{
             fctShowErrorField($field, true)
         }
    }
    else if(!doNoThing){
        
        if( typeof(fctShowErrorField) == 'undefined' ){
            DisplayError($field.attr('id'), 'error', false,4500);
         }
         else{
             fctShowErrorField($field, false)
         }
    }
    return result;
}

function CheckFormFields(containerForm){
    var $containerForm = $('#' + containerForm);
    var result = true;
     
    $.each($containerForm.find('.form-control'), function(index, value){
            if(!CheckField($(this))){
                result = false;
            }
    });
    
    return result;
}

function CheckField($field){
    var result = true;
    var type_result = 0; //0->no error; 1->field is empty; 2->value format is incorrect
    var type = $field.attr('data-format');
    var value = $field.val();
    var donothing =false;
    
        if($field.attr('data-required') == '1' && $.trim($field.val()) == ''){
            result = false;
            type_result = 1;
        }
        else if($field.is("select") &&  parseInt($field.val()) == -1){
            result = false;
            type_result = 1;
        }
        else if($field.attr('data-required') == '0' && $.trim($field.val()) == ''){
            donothing = true;
        }
        else{
            switch(type)
            {
                case 'char':
                    for(var i=0; i<value.length; i++){
                        if(!isNaN(value.charAt(i)) && $.trim(value.charAt(i)) != ''){
                            result = false;
                            type_result = 2;
                        }
                    }
                  break;
                case 'email':
                    var reg = new RegExp('^[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*@[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*[\.]{1}[a-z]{2,6}$', 'i');
                    if(!reg.test(value))
                    {
                           result = false;;
                           type_result = 2;
                    }
                  break;
                case 'number':
                    for(var i=0; i<value.length; i++){
                        if(isNaN(value.charAt(i))){
                            result = false;
                            type_result = 2;
                        }
                    }
                    break;

                default:

            }
        }
        
        if(!donothing){
            DisplayFieldCheckStatus($field, result, type_result);
        }
        else{
            DisplayFieldNeutralStatus($field);
        }  
    
    return result;
}

function DisplayFieldCheckStatus($field, $status, type_result){
    if($status){
        DisplayFieldRightStatus($field)
    }
    else{
        DisplayFieldWrongStatus($field, type_result)
    }
}

function DisplayFieldRightStatus($field){
        var $container = $field.parents('.form-group');
        $container.find('.icon-wrong').addClass('hide');
        $container.find('.help-block.help-field').addClass('hide');
        $container.find('.help-block.help-value').addClass('hide');
        $container.find('.icon-right').removeClass('hide');
        $field.removeClass('errorfield');
}
function DisplayFieldWrongStatus($field, type_result){
        var $container = $field.parents('.form-group');
        $container.find('.icon-wrong').removeClass('hide');
        if(type_result == 1 || $container.find('.help-block.help-value').length == 0){
            $container.find('.help-block.help-field').removeClass('hide');
            $container.find('.help-block.help-value').addClass('hide');
        }
        else{
            $container.find('.help-block.help-value').removeClass('hide');
            $container.find('.help-block.help-field').addClass('hide');
        }
        $container.find('.icon-right').addClass('hide');
        $field.addClass('errorfield');
}
function DisplayFieldNeutralStatus($field){
        var $container = $field.parents('.form-group');
        $container.find('.icon-wrong').addClass('hide');
        $container.find('.help-block.help-field').addClass('hide');
        $container.find('.help-block').addClass('hide');
        $container.find('.icon-right').addClass('hide');
        $field.removeClass('errorfield');
}
/***********************************************************************
****                  ERROR MESSAGES								****
/***********************************************************************/
function DisplayError(id,message,is_error,duration)
{	
        var thebox=id;
	$(thebox).html('<li>'+message+'</li>');
	$(thebox).toggleClass("error",is_error);
	$(thebox).toggleClass("success",!is_error);
	$(thebox).show();
	if (duration>0)
	{	$(thebox).delay(duration).fadeOut("slow");
	}
	$('#error_dialog_box').dialog({autoOpen:false,modal:true,width:630,position:{my:"center top", at:"center", of:"#BoiteSite"}}); // should only be executed in case of error
	$('#error_dialog_box').dialog("open");
}


function DisplayAjaxQueryResult(id,result)
{	if (result.success)
	{	DisplayError(id,result.message,false,4500);
	}
	else
	{	DisplayError(id,result.message,true,-1);
	}
}

function DisplayErrorFieldMessage(field,message)
{	jQuery.support.placeholder = (function(){
    var i = document.createElement('input');
    return 'placeholder' in i;
})();
	if (arguments.length == 1)
	{	message =$(field+"_error").html();
	}
	if (jQuery.support.placeholder)
	{	$(field).addClass("error");
		$(field).val("");
		$(field).attr("placeholder",message);
	}
	else
	{	$(field+"_error").html(message);
		$(field+"_error").show();
	}
}

function HideErrorFieldMessage(field)
{	jQuery.support.placeholder = (function(){
    var i = document.createElement('input');
    return 'placeholder' in i;
})();
if (jQuery.support.placeholder)
	{	$(field).removeClass("error");
		$(field).attr("placeholder","");
	}
	else
	{	$(field+"_error").hide();
	}
}
/***********************************************************************
****                   AJAX SAVER									****
/***********************************************************************/
function AjaxSaver(theform,theclass,action)
{	var result = result= {success:true,message:""};
	if(action==1)
	{	result=CheckForm();
	}
	if(action==3)
	{	result=ConfirmTransfert()
		result.nomessage =true;
	}
	// 4 is purge
	if(action==5)
	{	result=ConfirmImport()
		result.nomessage =true;
	}
	if (result.success)
	{	theform='#'+theform;
		if( result.hasOwnProperty('additional_params'))
		{	additional_params = "&"+result.additional_params;
		}
		else
		{	additional_params = "";
		}
		$.ajax(
		{	url:'/ajax/saver.ajax.php',
			type:'post',
			data:$(theform).serialize()+"&action="+action+"&class="+theclass+additional_params,
			success :function (data){result=data;},
			dataType:'json',
			async:false
		});
		return result;
		
	}
	else
	{	return result;
	}
}

function CallAjaxSaverAndSubmit(theform,theclass,action,display_id)
{	if (arguments.length == 3)
	{	display_id ='errormessagedisplay';
	}
	result = AjaxSaver(theform,theclass,action);
	if (result.success)
	{	$('#postback_message').val(result.message);
		$('#local_postback').val(result.message);
		$('#'+theform).submit();
	}
	else
	{	DisplayError('#'+display_id,result.message,true,-1);
	}
}

function CallAjaxSaverAndDisplay(theform,theclass,action,display_id)
{	result = AjaxSaver(theform,theclass,action);
	if (arguments.length == 3)
	{	display_id ='errormessagedisplay';
	}
	if( !(result.hasOwnProperty('nomessage')))
	{	if (result.success)
		{	DisplayError('#'+display_id,result.message,false,4000);
		}
		else
		{	DisplayError('#'+display_id,result.message,true,-1);
		}
	}
	else
	{	$('#'+display_id).hide();
	}
	return result;
}


/***********************************************************************
****                   STRINGS										****
/***********************************************************************/
function DebugTest(needs_ajax,pageName,thecontext)
{	if (needs_ajax==true)
	{	document.isogradstrings[pageName][thecontext] = javaGetStrings(pageName,thecontext);
	}
}

function javaGetGlobalStrings(pageName,thecontext)
{	if($.browser.msie && ( (parseInt($.browser.version, 10) == 7) || (parseInt($.browser.version, 10) == 8)))
	{	return  javaGetStrings(pageName,thecontext)
	}
	if (!document.hasOwnProperty("isogradstrings"))
	{	document.isogradstrings = new Array();
	}
	
	if (pageName in document.isogradstrings)
	{	var  needs_ajax= !(thecontext in document.isogradstrings[pageName]);
		DebugTest(needs_ajax,pageName,thecontext);
	}
	else
	{	document.isogradstrings[pageName] = new Array();
		document.isogradstrings[pageName][thecontext] = javaGetStrings(pageName,thecontext);
	}
	return document.isogradstrings[pageName][thecontext];
}

function javaGetStrings(pageName,context)
{	/* context is either tst or usr to decide which session variable has to be used*/
	/* if it is test getjavastrings will use tst_lan_id session variable otherwise it will use lan_id*/
	var temp;
	$.ajax(
	{	url:'/ajax/javastrings.ajax.php',
		type:'post', 
		data:{'page':pageName,'lan_id':context},
		dataType: "json",
		async:false,
		success:function (data) {temp=data;}
	});	
	return temp;
}

function javaGetGenericStrings(generic,context)
{	var temp;
	$.ajax(
	{	url:'/ajax/javagenericstrings.ajax.php',
		type:'post', 
		data:{'strings':generic,'lan_id':context},
		dataType: "json",
		async:false,
		success:function (data) {temp=data;}
	});	
	return(temp);
}



/***********************************************************************
****               Cookies											  ****
/***********************************************************************/
function cookies_enabled()
{
    var cookieEnabled = (navigator.cookieEnabled) ? true : false;

    if (typeof navigator.cookieEnabled == "undefined" && !cookieEnabled)
    { 
        document.cookie="testcookie";
        cookieEnabled = (document.cookie.indexOf("testcookie") != -1) ? true : false;
    }
    return (cookieEnabled);
}

/***********************************************************************
****               Buttons	 							  ****
/***********************************************************************/

function ShowWaitImage(thebutton)
{	 ShowWaitText(thebutton,"");
}


function HideWaitImage(thebutton)
{	HideWaitImage(thebutton);
}


function ShowWaitText(thebutton,thetext)
{	document.containerButtonWait = $('#'+thebutton).parents('.box-button'); 
    document.containerButtonWaitHtml =  document.containerButtonWait.html();
    document.containerButtonWait.html('<div id="messagewaiting">' + thetext + '</div>');
}

function HideWaitText(thebutton)
{	//var containerButtonWait = $('#messagewaiting').parents('.box-button'); 
    document.containerButtonWait.html(document.containerButtonWaitHtml);
}
/***********************************************************************
****               SELECT PACK							  ****
/***********************************************************************/

function SelectPack(pck_id)
{	$('#pck_id').val(pck_id);
	$("#formpack").submit();
}
function ChangeCurrency(lan_id){
        $.ajax(
	{	url:'/ajax/changecurrency.ajax.php',
		type:'post', 
		data:{ccy_id : $('#ccy_id').val(), lan_id : lan_id},
		dataType: "json",
		async:false,
		success:function (data) {
                    var $list_price = $.parseJSON(data.list_price);
                    for (var key in $list_price) {
                        $('#box-price-'+key+' .price-content').html($list_price[key]);
                    }
                }
	});
}
/***********************************************************************
****             HOW IT WORKS							  ****
/***********************************************************************/
function ToggleHowItWorks(){
	$('#box-howitworks ul').toggle();
	$('#box-howitworks ul li a').toggleClass('selected');
	return false;
}

/***********************************************************************
****             SUBSCRIBE NEWSLETTER   							  ****
/***********************************************************************/
function SubscribeNewsletter(){
	
		   $.ajax(
			{	url:'/ajax/subscribenewsletter.ajax.php',
				type:'post', 
				data:{ema : $('#aside_sub_news_ema').val()},
				dataType: "json",
				async:false,
				success:function (data) {
							if(data.success){
								$("#dialog_subscribe_newsletter .message_content").removeClass('error');
								$("#aside_sub_news_ema").attr('disabled','disabled');
								$('#submit_subscribenewsletter').addClass('hide');
							}
							else{
								$("#dialog_subscribe_newsletter .message_content").addClass('error');
							}
							$("#dialog_subscribe_newsletter .message_content").html(data.message);
							$("#dialog_subscribe_newsletter").dialog({modal:true,width:500, resizable:false});
						}
			});
}