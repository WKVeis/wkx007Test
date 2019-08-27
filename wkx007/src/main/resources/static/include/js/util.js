var util = {};
util.isNumber = function (value) {
    return /^\d+\.*\d*$/.test(value)
}
util.isDate = function (value) {
    try {
        return value && new Date(value);
    }
    catch (ex) {
        return false;
    }
}
util.isJson = function (value) {
    try {
        return value && JSON.parse(value);
    }
    catch (ex) {
        return false;
    }
}

util.formatDateTime = function (date) {
    if (typeof (date) == "string") {
        date = new Date(date.replace(/\-/g,"/"));
    }
    var year = date.getFullYear()
    var month = date.getMonth() + 1
    var day = date.getDate()
    var hour = date.getHours()
    var minute = date.getMinutes()
    var second = date.getSeconds()

    return [year, month, day].map(util.formatNumber).join('-') + ' ' + [hour, minute, second].map(util.formatNumber).join(':')
}

util.formatDate = function (date) {
    
    if (typeof (date) == "string") {
        date = new Date(date.replace(/\-/g,"/"));
    }
   
    console.log("date2",date.toString());
    var year = date.getFullYear()
    var month = date.getMonth() + 1
    var day = date.getDate()
    var hour = date.getHours()
    var minute = date.getMinutes()
    var second = date.getSeconds()

    return [year, month, day].map(util.formatNumber).join('-')
}

util.formatTime = function (date) {
    if (typeof (date) == "string") {
        date = new Date(date.replace(/\-/g,"/"));
    }
    var year = date.getFullYear()
    var month = date.getMonth() + 1
    var day = date.getDate()
    var hour = date.getHours()
    var minute = date.getMinutes()
    var second = date.getSeconds()

    return [hour, minute, second].map(util.formatNumber).join(':')
}

util.formatNumber = function (n) {
    n = n.toString()
    return n[1] ? n : '0' + n
}

Date.prototype.add = function (interval, value) {
    var d = this;
    switch (interval) {
        case 'milli':
            d.setMilliseconds(this.getMilliseconds() + value);
            break;
        case 'second':
            d.setSeconds(this.getSeconds() + value);
            break;
        case 'minute':
            d.setMinutes(this.getMinutes() + value);
            break;
        case 'hour':
            d.setHours(this.getHours() + value);
            break;
        case 'day':
            d.setDate(this.getDate() + value);
            break;
        case 'month':
            var day = this.getDate();
            if (day > 28) {
                day = Math.min(day, this.getFirstDateOfMonth().add('month', value).getLastDateOfMonth().getDate());
            }
            d.setDate(day);
            d.setMonth(this.getMonth() + value);
            break;
        case 'year':
            d.setFullYear(this.getFullYear() + value);
            break;
    }
    return d;
}

util.cookie = {
    get: function (name) {
        if (document.cookie.length > 0) {
            var c_start = document.cookie.indexOf(name + "=");
            if (c_start != -1) {
                c_start = c_start + name.length + 1;
                c_end = document.cookie.indexOf(";", c_start)
                if (c_end == -1) c_end = document.cookie.length
                return unescape(document.cookie.substring(c_start, c_end))
            }
        }
        return ""
    },
    set: function (name, value, exp, path) {
        exp = exp || 0;
        path = path || "/";
        var exdate = new Date().add("day", exp || 0);
        document.cookie = name + "=" + escape(value) + ";expires=" + exdate.toGMTString() + ";path=" + path;
    }
}