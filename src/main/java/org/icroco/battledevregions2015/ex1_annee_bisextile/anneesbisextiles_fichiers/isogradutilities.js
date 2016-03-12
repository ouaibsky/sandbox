/**************SVG BUTTON Creation ***********************/
$(function () {
    $('div.box-button').uniqueId();
   AdjustButtons('div.box-button');
});


function DarkenColor(rgb) {
    var darkenPercent = 20; // darken color by 15 percent
    rgb = rgb.replace('rgb(', '').replace(')', '').split(',');
    var red = $.trim(rgb[0]);
    var green = $.trim(rgb[1]);
    var blue = $.trim(rgb[2]);
    // darken
    red = parseInt(red * (100 - darkenPercent) / 100);
    green = parseInt(green * (100 - darkenPercent) / 100);
    blue = parseInt(blue * (100 - darkenPercent) / 100);
    rgb = 'rgb(' + red + ', ' + green + ', ' + blue + ')';
    return rgb;
}

function AdjustButtons(mainSelector) {
   if (!CheckIsIpad() &&($('#dont_use_svg').length==0)) {
        document.max_cut_button_size = 0;
        $(mainSelector).each(function () {
            var p1,p2,p3,p4,svg,color,hover_color,myheight,mywidth,button;
			 mywidth = parseInt($(this).css("width")) + 2;//+2 is for a bug when buttons 
            if ((mywidth > 5) && ($(this).data("svg_done") != 1)) {
                myheight = parseInt($(this).css("height"));
                document.max_cut_button_size = Math.max(myheight, document.max_cut_button_size);
                svg = '<svg height="' + myheight + '" width="' + mywidth + '" class="cut">';
                button = $(this).find('a');
				if($(button).hasClass("blue")){
					color = "rgb(45, 84, 112)";
				}
				else
				{	if($(button).hasClass("pink")){
						color ="rgb(235, 95, 105)";
					}
					else
					{	if($(button).hasClass("grey")){
							color ="rgb(153,​ 153,​ 153)";
						}
						else
						{
							color = "rgb(45, 84, 112)";
						}
					}
				}
                hover_color = DarkenColor(color);
                $(this).find('a').css("background-color", "transparent");
                if ($(this).hasClass("cut-top")) {
                    p1 = "M5 " + (0.44) * myheight;
                    p2 = "L5 " + myheight;
                    p3 = "L" + (0.95) * mywidth + " " + myheight;
                    p4 = "L" + (0.95) * mywidth + " " + (0.14) * myheight + "Z";

                } else
                {
                    p1 = "M5 " + (0.05) * myheight;
                    p2 = "L" + (0.95) * mywidth + " " + (0.05) * myheight;
                    p3 = "L" + (0.95) * mywidth + " " + (0.60) * myheight;
                    p4 = "L5 " + (0.90) * myheight + "Z";

                }
                svg += '<path d="' + p1 + ' ' + p2 + ' ' + p3 + ' ' + p4 + '"';
                svg += ' style="fill:' + color + ';stroke:' + color + ';">';
                svg += '</svg>';
                $(this).html(svg + $(this).html());
                svg = '<svg height="' + myheight + '" width="' + mywidth + '" style="display:none;" class="hover">';
                svg += '<path d="' + p1 + ' ' + p2 + ' ' + p3 + ' ' + p4 + '"';
                svg += ' style="fill:' + hover_color + ';stroke:' + hover_color + ';">';
                svg += '</svg>';
                $(this).html(svg + $(this).html());
                $(this).find('a').css("position", "absolute");
                $(this).css("height", myheight);
                $(this).data("svg_done", 1);
                $(this).hover(function () {
                    $(this).find("svg.cut").hide();
                    $(this).find("svg.hover").show();
                }, function () {
                    $(this).find("svg.hover").hide();
                    $(this).find("svg.cut").show();
                });
                if ((myheight < 35) && (document.max_cut_button_size > 29)) {
                    //this is an x-small button and there is a small button
                    //then we have two cases depending on dialogs
                    if ($(this).closest('div.mydialog').length == 0) {
                        $(this).css("bottom", "-15px");
                    } else {
                        $(this).css("bottom", "-12px");
                    }
                }
            }
        });
    }
	if($('#dont_use_svg').length!=0){
		$('div.box-button').addClass("nosvg");
	}
}
/**************BROWSER DETECTION ***********************/
var matched, browser;

// Use of jQuery.browser is frowned upon.
// More details: http://api.jquery.com/jQuery.browser
// jQuery.uaMatch maintained for back-compat
jQuery.uaMatch = function (ua) {
    ua = ua.toLowerCase();

    var match = /(chrome)[ \/]([\w.]+)/.exec(ua) ||
            /(webkit)[ \/]([\w.]+)/.exec(ua) ||
            /(opera)(?:.*version|)[ \/]([\w.]+)/.exec(ua) ||
            /(msie) ([\w.]+)/.exec(ua) ||
            ua.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec(ua) ||
            [];

    return {
        browser: match[ 1 ] || "",
        version: match[ 2 ] || "0"
    };
};

matched = jQuery.uaMatch(navigator.userAgent);
browser = {};
if (matched.browser) {
    browser[ matched.browser ] = true;
    browser.version = matched.version;
    browser.name = matched.browser;
}

// Chrome is Webkit, but Webkit is also Safari.
if (browser.chrome) {
    browser.webkit = true;
    browser.name = "chrome";
} else if (browser.webkit) {
    browser.safari = true;
    browser.name = "safari";
}

if (!browser.msie) {
    if (!!navigator.userAgent.match(/Trident\/7\./)) {
        browser.msie = true;
        browser.mozilla = false;
        browser.version = 11;
        browser.name = "msie";
    }

    if (!!navigator.userAgent.match(/Trident\/6\./)) {
        browser.msie = true;
        browser.mozilla = false;
        browser.version = 10;
        browser.name = "msie";
    }
} else {
    //safety
    browser.name = "msie";
}
jQuery.browser = browser;

function CheckIECompatibility() {
    var engine = 100;
    if (window.navigator.appName == "Microsoft Internet Explorer")
    {
        // This is an IE browser. What mode is the engine in?
        if (document.documentMode) // IE8 or later
            engine = document.documentMode;
        else // IE 5-7
        {
            engine = 5; // Assume quirks mode unless proven otherwise
            if (document.compatMode)
            {
                if (document.compatMode == "CSS1Compat")
                    engine = 7; // standards mode
            }
            // There is no test for IE6 standards mode because that mode  
            // was replaced by IE7 standards mode; there is no emulation.
        }
        // the engine variable now contains the document compatibility mode.
    }

    if (engine > 8) {
        return true;
    } else {
        return false;
    }
}


function CheckIsIpad() {
    return navigator.userAgent.match(/iPad/i) != null;
}
/***********************************************************************
 ****                   OS DETECTCTION								****
 /***********************************************************************/

function GetOS() {
    var OSName = "Unknown OS";
    if (navigator.appVersion.indexOf("Win") != -1)
        OSName = "Windows";
    else if (navigator.appVersion.indexOf("Mac") != -1)
        OSName = "MacOS";
    return OSName;
}

/***********************************************************************
 ****                   AJAX SAVER									****
 /***********************************************************************/
function AjaxSaver(theform, theclass, action)
{
    var result = new Object();
	var additional_params;

    result.success = true;
    if ((action == 1) || (action == 7))
    {
        result = CheckForm();
    }
    if (action == 3)
    {
        result = ConfirmTransfert()
        result.nomessage = true;
    }
    // 4 is purge
    if (action == 5)
    {
        result = ConfirmImport()
        result.nomessage = true;
    }

    if (result.success)
    {
        theform = '#' + theform;
        if (result.hasOwnProperty('additional_params'))
        {
            additional_params = "&" + result.additional_params;
        }
        else
        {
            additional_params = "";
        }
        $.ajax(
                {url: '/ajax/saver.ajax.php',
                    type: 'post',
                    data: $(theform).serialize() + "&action=" + action + "&class=" + theclass + additional_params,
                    success: function (data) {
                        result = data;
                    },
                    dataType: 'json',
                    async: false
                });
        // ajax error messages are never displaed in fields
        result.use_fields_for_error = false;
        return result;

    }
    else
    {
        return result;
    }
}

function CallAjaxSaverAndSubmit(theform, theclass, action, display_id)
{

    if (arguments.length == 3)
    {
        display_id = 'errormessagedisplay';
    }
    var result = AjaxSaver(theform, theclass, action);
    if (result.success)
    {
		console.log(result.message);
        $('#' + theform + ' #postback_message').val(result.message);
        $('#local_postback').val(result.message);
        $('#' + theform).submit();
    }
    else
    {	// if checkform uses field error then we do not do anything
        if (!(result.hasOwnProperty('use_fields_for_error')) || !result.use_fields_for_error)
        {
            DisplayError('#' + display_id, result.message, true, -1);
        }
    }
}

function CallAjaxSaverAndDisplay(theform, theclass, action, display_id)
{
    var result = AjaxSaver(theform, theclass, action);
    if (arguments.length == 3)
    {
        display_id = '#errormessagedisplay';
    } else {
        display_id = "#" + display_id;
    }
    // ajax error messages are never displaed in fields
    if (!(result.hasOwnProperty('nomessage')))
    {
        if (result.success)
        {
            DisplayError(display_id, result.message, false, 4000);
        }
        else
        {
            if ((!(result.hasOwnProperty('use_fields_for_error')) || !result.use_fields_for_error) && (typeof result.message != 'undefined'))
            {
                DisplayError(display_id, result.message, true, -1);
            }
            else {
                $(display_id).hide();
            }
        }
    }
    else
    {
        $(display_id).hide();
    }
    return result;
}
/***********************************************************************
 ****                   CheckMandatoryFields						****
 /***********************************************************************/
function SetEventForm(form) {
    $('#' + form + ' .form-control').change(function () {
        CheckField($(this));
    });
}

function SetEventPassword(id_psw) {
    $('#' + id_psw).change(function () {
        if ($.trim($('#confirmpsw').val()) != '') {
            CheckPasswordConfirm(id_psw);
        }
    });

    $('#confirmpsw').change(function () {
        CheckPasswordConfirm(id_psw);
    });
}

function CheckMandatoryFields(fields, theform)
{
    var result = {success: true, message: "", use_fields_for_error: true};
	var iter;

    $('#' + theform + ' form').each(function () {
        HideErrorFieldMessage(this)
    });
    for (iter = 0; iter < fields.length; iter++)
    {
        if ($('#' + fields[iter]).val().length < 3)
        {
            DisplayErrorFieldMessage('#' + fields[iter]);
            result.success = false;
        }
    }
    return result;
}

function CheckFormFields(containerForm) {
    var $containerForm = $('#' + containerForm);
    var result = {};
    result.success = true;

    $.each($containerForm.find('.form-control'), function (index, value) {
        if (!CheckField($(this))) {
            result.success = false;
        }
    });

    return result;
}

function CheckField($field) {
    var result = true;
    var type = $field.attr('data-format');
    var value = $field.val();
    var donothing = false;

    if ($field.attr('data-required') == '1' && $.trim($field.val()) == '') {
        result = false;
    }
    else if ($field.attr('data-required-sel') == '1' && parseInt($field.val()) == -1) {
        result = false;
    }
    else if ($field.attr('data-required') == '0' && $.trim($field.val()) == '') {
        donothing = true;
    }
    else {
        switch (type)
        {
            case 'char':
                for (var i = 0; i < value.length; i++) {
                    if (!isNaN(value.charAt(i)) && $.trim(value.charAt(i)) != '') {
                        result = false;
                    }
                }
                break;
            case 'email':
                var reg = new RegExp('^[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*@[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*[\.]{1}[a-z]{2,6}$', 'i');
                if (!reg.test(value))
                {
                    result = false;
                    ;
                }
                break;
            case 'number':
                for (var i = 0; i < value.length; i++) {
                    if (isNaN(value.charAt(i))) {
                        result = false;
                    }
                }
                break;

            default:

        }
    }

    if (!donothing) {
        DisplayFieldCheckStatus($field, result);
    }
    else {
        DisplayFieldNeutralStatus($field);
    }


    return result;
}

function CheckPasswordConfirm(id_psw)
{
    var result = {};
    result.success = true;

    if ($('#' + id_psw).val().length < 6)
    {
        result.success = false;
        DisplayFieldWrongStatus($('#' + id_psw));
    }

    if ($('#' + id_psw).val() != $('#confirmpsw').val())
    {
        result.success = false;
        DisplayFieldWrongStatus($('#confirmpsw'));
    }



    return result;
}

function DisplayFieldCheckStatus($field, $status) {
    if ($status) {
        DisplayFieldRightStatus($field)
    }
    else {
        DisplayFieldWrongStatus($field)
    }
}

function DisplayFieldRightStatus($field) {
    var $container = $field.parents('.form-group');
    $container.find('.icon-wrong').addClass('hide');
    $container.find('.help-block').addClass('hide');
    $container.find('.icon-right').removeClass('hide');
    $field.removeClass('errorfield');
}
function DisplayFieldWrongStatus($field) {
    var $container = $field.parents('.form-group');
    $container.find('.icon-wrong').removeClass('hide');
    $container.find('.help-block').removeClass('hide');
    $container.find('.icon-right').addClass('hide');
    $field.addClass('errorfield');
}

function DisplayFieldNeutralStatus($field) {
    var $container = $field.parents('.form-group');
    $container.find('.icon-wrong').addClass('hide');
    $container.find('.help-block').addClass('hide');
    $container.find('.icon-right').addClass('hide');
    $field.removeClass('errorfield');
}

/***********************************************************************
 ****                  GoBackToLogin									****
 /***********************************************************************/
function GoBackToLogin()
{
    document.location = "https://www.isograd.com/login.php";
}
/***********************************************************************
 ****                    DISPLAY ERROR								****
 /***********************************************************************/
function DisplayError(id, message, is_error, duration)
{
    var thebox = id;
    if (is_error) {
        message = '<i class="fa fa-times-circle-o"></i>' + message;
    } else {
        message = '<i class="fa fa-check-circle-o"></i>' + message;
    }
    $(thebox).html(message);
    if (is_error)
    {
        $(thebox).addClass("error");
        $(thebox).removeClass("success");
        $(thebox).show();
    }
    else
    {
        $(thebox).addClass("success");
        $(thebox).removeClass("error");
        $(thebox).show();
    }
    if (duration > 0)
    {
        $(thebox).delay(duration).fadeOut("slow");
    }
}


function DisplayAjaxQueryResult(id, result)
{
    if (result.success)
    {
        DisplayError(id, result.message, false, 4500);
    }
    else
    {
        DisplayError(id, result.message, true, -1);
    }
}

function DisplayPostBackMessage(display_id)
{
    if (arguments.length == 0)
    {
        display_id = '#errormessagedisplay';
    }
    if (($('#postback_message').length != 0) && ($('#postback_message').val().length > 3))
    {
        DisplayError(display_id, $('#postback_message').val(), false, 4500);
        $('#postback_message').val(""); // so that it does not display on next page
    }
}


// This function either displays in a placeHolder or shows an error message around the field

function DisplayErrorFieldMessage(field, message)
{
    jQuery.support.placeholder = (function () {
        var i = document.createElement('input');
        return 'placeholder' in i;
    })();
    if (arguments.length == 1)
    {
        message = $(field + "_error").html();
    }
    if (jQuery.support.placeholder)
    {
        $(field).addClass("error");
        $(field).val("");
        $(field).attr("placeholder", message);
    }
    else
    {
        $(field + "_error").html(message);
        $(field + "_error").show();
    }
}

function HideErrorFieldMessage(field)
{
    jQuery.support.placeholder = (function () {
        var i = document.createElement('input');
        return 'placeholder' in i;
    })();
    if (jQuery.support.placeholder)
    {
        $(field).removeClass("error");
        $(field).attr("placeholder", "");
    }
    else
    {
        $(field + "_error").hide();
    }
}

/***********************************************************************
 ****                   STRINGS										****
 /***********************************************************************/
jQuery.extend({
    stringify: function stringify(obj) {
        if ("JSON" in window) {
            return JSON.stringify(obj);
        }

        var t = typeof (obj);
        if (t != "object" || obj === null) {
            // simple data type
            if (t == "string")
                obj = '"' + obj + '"';

            return String(obj);
        } else {
            // recurse array or object
            var n, v, json = [], arr = (obj && obj.constructor == Array);

            for (n in obj) {
                v = obj[n];
                t = typeof (v);
                if (obj.hasOwnProperty(n)) {
                    if (t == "string") {
                        v = '"' + v + '"';
                    } else if (t == "object" && v !== null) {
                        v = jQuery.stringify(v);
                    }

                    json.push((arr ? "" : '"' + n + '":') + String(v));
                }
            }

            return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
        }
    }
});


function javaGetStrings(pageName, context)
{	/* context is either tst or usr to decide which session variable has to be used*/
    /* if it is test getjavastrings will use tst_lan_id session variable otherwise it will use lan_id*/
    var temp;
    $.ajax(
            {url: '/ajax/javastrings.ajax.php',
                type: 'post',
                data: {'page': pageName, 'lan_id': context},
                dataType: "json",
                async: false,
                success: function (data) {
                    temp = data;
                }
            });
    return temp;
}

function javaGetGenericStrings(generic, context)
{
    var temp;

    $.ajax(
            {url: '/ajax/javagenericstrings.ajax.php',
                type: 'post',
                data: {'strings': generic, 'lan_id': context},
                dataType: "json",
                async: false,
                success: function (data) {
                    temp = data;
                }
            });
    return(temp);
}
/***********************************************************************
 ****                   TABLES									  ****
 /***********************************************************************/
function ProcessParams(params, me, changelinks, changelinks_params)
{
    var localparams = jQuery.extend(true, {}, params); // very important to clone the boject
    var string = '', iter;
    if (changelinks != '')
    {
        localparams = changelinks(me, localparams, changelinks_params);
    }
    for (iter = 0; iter < localparams.fields.length; iter++)
    {
        $('#' + localparams.fields[iter].id).val($(me).data(localparams.fields[iter].source));
    }
    for (iter = 0; iter < localparams.action.length; iter++)
    {
        string += '<a onclick="' + localparams.action[iter].action + '" style="padding-right:15px;">' + localparams.action[iter].label + '</a>';
    }
    return string;
}

function SetTableHover()
{	var params, changelinks, changelinks_params,table_id,string;
    params = arguments[0];
    if (arguments.length >= 2)
    {
        changelinks = arguments[1];
        if (arguments.length == 3)
        {
            changelinks_params = arguments[2];
        }
    }
    else
    {
        changelinks = '';
        changelinks_params = '';
    }
	if(params.hasOwnProperty('table')){
		table_id = params.table+" ";
	}else{
		table_id = '';
	}
    $(table_id+"tbody tr").hover(
            function () {
                $(this).addClass('mouseisover');
                string = ProcessParams(params, this, changelinks, changelinks_params);
                $(this).find("td:last").html(string);
            },
            function () {
                $(this).removeClass('mouseisover');
                $(this).find("td:last").html('');
            }
    );
}

function SetTableHoverWithHide()
{
    var params = arguments[0];
    var colspan = arguments[1];
	var changelinks,changelinks_params,string,temp;
    if (arguments.length >= 3)
    {
        changelinks = arguments[2];
        if (arguments.length == 4)
        {
            changelinks_params = arguments[3];
        }
    }
    else
    {
        changelinks = '';
        changelinks_params = '';
    }
    $("tbody tr").hover(
            function () {
                string = ProcessParams(params, this, changelinks, changelinks_params);
                $(this).addClass('mouseisover');
                $(this).find("td.tohide").hide();
                $(this).find("td.toshow").attr("colspan", colspan);
                $(this).find("td.toshow").attr("padding-right", "0px");
                temp = $(this).find("td.toshow").text();
                $(this).find("td.toshow").data("temp", temp);
                $(this).find("td.toshow").html(string);
            },
            function () {
                $(this).removeClass('mouseisover');
                $(this).find("td.toshow").html('');
                $(this).find("td.toshow").attr("colspan", "1");
                $(this).find("td.toshow").attr("padding-right", "40px");
                temp = $(this).find("td.toshow").data("temp");
                $(this).find("td.toshow").text(temp);
                $(this).find("td.tohide").show();
            });
}

function SortAdminBy(sortfield, theform, action)
{
    var thejqueryform = '#' + theform;
    var thesortfield = thejqueryform + ' #orderby';
    var thechangepagefield = thejqueryform + ' #changepage';
    var thecurrentsort = $(thesortfield).val();
    var thenewsort;
    if (thecurrentsort == sortfield + " ASC")
    {
        thenewsort = sortfield + " DESC";
    }
    else
    {
        thenewsort = sortfield + " ASC";
    }
    $(thesortfield).val(thenewsort);
    $(thechangepagefield).val(1);
    $(thejqueryform).attr('action', action).submit();
}

function SetSortArrow(thevalue)
{
    var temp = new String(thevalue);
    var thearray = temp.split(" ");
    var thecolumn = '#column_' + thearray[0].replace(/[^a-zA-Z0-9]/g, "");
    if (thearray[1] == "ASC")
    {
        $(thecolumn).addClass("sortasc");
    }
    else
    if (thearray[1] == "DESC")
    {
        $(thecolumn).addClass("sortdesc");
    }
    AdjustTableSize();
}

function ResetPage(theform, thepage)
{
    var thejqueryform = '#' + theform;
    var thefield = thejqueryform + ' input[name=changepage]';
    $(thefield).val(thepage);
    $(thejqueryform).submit();
}


function AdjustTableSize()
{	var target_width = parseInt($('table').parent().css("width"));
	var current_width,offset,last_cell_width,thetop;
    $("thead tr").each(
            function ()
            {
                current_width = parseInt($(this).css("width")) + parseInt($(this).css("padding-left")) + parseInt($(this).css("padding-right"));
                offset = Math.max(target_width - current_width, 0);
                last_cell_width = parseInt($(this).find("th:last").css("width")) + offset;
                $(this).find("th:last").css("width", last_cell_width + "px");
            }
    );
    if ($('table:eq(0)').length > 0)
    {
        thetop = $('table:eq(0)').position().top;
        $('div.admincontent ul.pagenavigation.fortable').css("top", thetop + 8);
    }
}
/***********************************************************************
 ****                ProgressBar	 							  ****
 /***********************************************************************/
var timer_interval_wait;

function CreateProgressbar(id, duration)
{	var htmlProgressbar;
    htmlProgressbar = '<div class="progress">';
    htmlProgressbar += '<div class="progress-bar progress-bar-striped active"  role="progressbar" aria-valuenow="1" aria-valuemin="0" aria-valuemax="100" style="width: 1%">';
    htmlProgressbar += '<span class="sr-only"></span>';
    htmlProgressbar += '</div>';
    htmlProgressbar += '</div>';

    $(id).append(htmlProgressbar);

    AnimateProgressBar(duration * 10)
}

function AnimateProgressBar(speed) {
    var timer_interval_wait;
	$('.progress-bar').css('width', '0%');
    var progress = 0;
    clearInterval(timer_interval_wait);
    timer_interval_wait = setInterval(function () {
        progress = progress + 1;
        if (progress > 100) {
            clearInterval(timer_interval_wait);
        }
        $('.progress-bar').css('width', progress + '%');
    }
    , speed);
}

function StartExport(theform, thepage)
{
    $('#export_to_excel').val(1);
    $('#' + theform).attr('action', thepage).submit();
}
/***********************************************************************
 ****                Export to Excel		 							  ****
 /***********************************************************************/
function ExportToExcel()
{
    var ExportToExcel_result = new Array();
    var ExportToExcel_row = 0;
    var ExportToExcel_column = 0;
    var temp,iter,iter2;
    if ($('#export_to_excel').val() == 1)
    {
        ExportToExcel_result[ExportToExcel_row] = new Array(); 
		$('table.export tr').each(function ()
        {
            ExportToExcel_column = 0;
            $(this).find("th,td").each(function () {
                ExportToExcel_result[ExportToExcel_row][ExportToExcel_column] = $(this).text();
                ExportToExcel_column++;
            });
            ExportToExcel_row++;
            ExportToExcel_result[ExportToExcel_row] = new Array();
        });
        // we split the array in bucket of 50 rows
        var action = 1;
        for (iter = 0; iter < ExportToExcel_result.length; iter += 30)
        {
            temp = new Array();
            for (iter2 = 0; iter2 < 30; iter2++)
            {
                temp[iter2] = ExportToExcel_result[iter + iter2];
            }
            $.ajax(
                    {url: '/ajax/exporttoexcel.ajax.php',
                        type: 'post',
                        dataType: "json",
                        data: {"content": temp, "action": action},
                        async: false,
                        success: function (data) {
                            result = data;
                        }
                    });
            action = 2;
        }
        // we call one last time to generate the file with action=2
        $.ajax(
                {url: '/ajax/exporttoexcel.ajax.php',
                    type: 'post',
                    dataType: "json",
                    data: {"action": 3},
                    async: false,
                    success: function (data) {
                        result = data;
                    }
                });

        if (result.success)
        {
            $('#Export_Button').html(result.message);
        }
        $('#export_to_excel').val(0);
        SetSortArrow($('#orderby').val());
    }
}
/***********************************************************************
 ****               Buttons	 							  ****
 /***********************************************************************/


function ShowWaitText(thebutton, thetext)
{	thebutton = "#"+thebutton;
   $(thebutton).hide();
   $(thebutton).parent().find("label.wait").html(thetext);
   $(thebutton).parent().find("label.wait").css("display","inline-block");
}


function HideWaitText(thebutton)
{	thebutton = "#"+thebutton;
	$(thebutton).show();
	$(thebutton).parent().find("label.wait").hide();
}
/***********************************************************************
 ****               DATEPICKER MULTILINGUAL	 					  ****
 /***********************************************************************/

function PrivateSetLanguageForDatePicker(thefield, thelan, thedateformat,with_time)
{
    if (thelan != 1)
    {
        $.datepicker.setDefaults($.datepicker.regional['']);
    }
    else
    {
        $.datepicker.setDefaults($.datepicker.regional['fr']);
		if(with_time){
			$.timepicker.setDefaults($.timepicker.regional['fr']);
		}
    }
	
    if (thedateformat == "d/m/y")
    {	if(with_time){
			 $("#" + thefield).datetimepicker({changeMonth: true, changeYear: true, dateFormat: 'dd/mm/y', timeFormat:'HH:mm',});
		}else{
			 $("#" + thefield).datepicker({changeMonth: true, changeYear: true, dateFormat: 'dd/mm/y'});
		 }
    }
    else
    {	if(with_time){
			$("#" + thefield).datetimepicker({changeMonth: true, changeYear: true, dateFormat: 'mm/dd/y', timeFormat:'HH:mm'});
		}
		else{
			$("#" + thefield).datepicker({changeMonth: true, changeYear: true, dateFormat: 'mm/dd/yy'});
		}
    }
    $("#" + thefield).change(function () {
        $('#changepage').val(1);
    });
}

function SetLanguageForDateTimePicker(thefield, thelan, thedateformat){
	PrivateSetLanguageForDatePicker(thefield, thelan, thedateformat,true);
}

function SetLanguageForDatePicker(thefield, thelan, thedateformat){
	PrivateSetLanguageForDatePicker(thefield, thelan, thedateformat,false);
}
/***********************************************************************
 ****              SEARCH FIELD				  ****
 /***********************************************************************/
function ActivateSearchField(thefield, theform)
{
    $('#' + thefield).keypress(function (event)
    {
        if (event.which == 13)
        {
            event.preventDefault();
            ResetPage(theform, 1);
        }
    });
    $('#' + thefield).focus();
}
/***********************************************************************
 ****              Toggle Param				  ****
 /***********************************************************************/
function toggleparam(param)
{
    $.ajax({url: '/questions/ajax/switchparam.ajax.php', type: 'post', data: {what: param}, async: false});
    $('div#BoiteSite form').first().submit();
}

/***********************************************************************
 ****    VIEW ANSWSER / DISPLAY QUESTION				      ****
 /***********************************************************************/

function DisplayQuestion(que_str_id, theform)
{
    $.ajax(
            {url: '/ajax/viewoneanswer.ajax.php',
                type: 'post',
                data: {str_id: que_str_id},
                async: false}
    );
    $(window).off('beforeunload');
    $('#' + theform).attr('action', '/runtest/runtest.php').submit();
}

/***********************************************************************
 ****    MANAGE DIALOG							      ****
 ***********************************************************************/

function OpenDialog(id, parameters) {

    if (!parameters.hasOwnProperty('resizable')) {
        parameters.resizable = false;
    }
    if (!parameters.hasOwnProperty('modal')) {
        parameters.modal = true;
    }
    parameters.modal = true;
    parameters.open = function (event, ui) {
        AdjustButtons("#" + id + " div.box-button");
    };
    //closeonescape only works when the focus is on the dialog
    //focus in only activated if there is at least on input in the dialog
    $('#' + id).dialog(parameters);
    if(parameters.hasOwnProperty("closeOnEscape")){
       if($("#"+id).find("input").length==0){
           $("#"+id+ " a.button").focus();
       }
    }
}

/***********************************************************************
 ****    SHOW AND HIDE FIELDS IN DIPSPLAY GROUP					****
 /***********************************************************************/
function ManageDisplayedFields(list_show, list_hide, forced_values, formname) {
    //we start by hiding the fields, this is important
    //as if a field is both hidden and shown it should be shown
    list_hide.forEach(function (entry) {
        $("#" + formname).find('[name="' + entry + '"]').parent('.form-group').hide();
    });

    list_show.forEach(function (entry) {
        $("#" + formname).find('[name="' + entry + '"]').parent('.form-group').show();
    });

    for (var keys in forced_values) {
        $('#' + keys).val(forced_values[keys]);
    }
}

function SetFieldVisibility(formname) {
    $.ajax(
            {url: '/ajax/getfieldsvisibility.ajax.php',
                type: 'post',
                data: $('#' + formname).serialize(),
                async: false,
                dataType: 'json',
                success: function (data) {
                    if (data.success) {
                        ManageDisplayedFields(data.list_show, data.list_hide, data.forced_values, formname);
                    }
                }
            });
}

/***********************************************************************
 ****    MANAGE SUB GROUP					****
 /***********************************************************************/

function GetGroupOrSubGroupId() {
    if ($('#filter_sub_dep_id').val() == undefined || $('#filter_sub_dep_id').val() == -1) {
        return $('#filter_dep_id').val()
    }
    else {
        return $('#filter_sub_dep_id').val();
    }
} 
/***********************************************************************
 ****    ALERT DIALOG					****
 /***********************************************************************/
function ShowDisabledDialog(){
    var Strings =  javaGetStrings("showdisableddialog.function", "usr"); //63;
    $('#alert_dialog .message').html(Strings[1]);
    OpenDialog('alert_dialog', {width: 400}); 
}
/***********************************************************************
 ****   MULTILINGUAL DESCRIPTION IN READY_TEST AND CATEGORIES					****
 /***********************************************************************/
function ChangeLanguageDescription(){
	var lan_id = $('#des_lan_id').val();
	$('div.lan_description_container').hide();
	$('#tst_des_'+lan_id).show();
	
}

/***********************************************************************
 ****   MULTISELET MANAGEMENT										****
 /***********************************************************************/
function CreateMultiSelect(){
	$('select').each(function(index,element){
			if($(element).prop("multiple")){
				var dataarray= new String($(element).data("multi-value"));
				var string_select,string_placeholder;
				$(element).val(dataarray.split("%,%"));
				if($('body').data('lan_id')==1){
					if($(element).data("label_gender")==1){
						string_select = "sélectionnés";
					}else{
						string_select = "sélectionnées";
					}
					string_placeholder = "Sélectionner...";
				}else{
					 string_select = "selected";
					 string_placeholder = "Select here...";
				}
				$(element).SumoSelect( {captionFormat: '{0} '+string_select, placeholder:string_placeholder, csvDispCount:$(element).data("csvdispcount")});
			}
	});
}

function RetrieveMultiSelect(){
	$('select').each(function(index,element){
			if($(element).prop("multiple")){
				var current_multi_select_value = {};
				var id = '#'+$(element).prop("id");
				$(id+' option:selected').each(function (index, myoption) {
					current_multi_select_value[index] = $(myoption).val();
				});
				$(id+'_ids').val(JSON.stringify(current_multi_select_value));
			}
	});
}

function LogAsAlias(id)
{
    $.ajax(
	    {url: '/clients/ajax/hyperadmin/logasalias.ajax.php',
		type: 'post',
		data: {"com_id": $('#com_id').val()},
		dataType: 'json',
		async: false,
		success: function(data) {
		    result = data;
		}
	    });
    if (result.success)
    {
	$('#orderby').val(null);
	$('#filter_search').val(null);
	$('#'+id).attr("action", "/clients/admincandidate.php").submit();
    }
}