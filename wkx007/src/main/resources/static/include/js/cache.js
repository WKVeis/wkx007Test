var list = [];
var cache = {};

cache.set = function (key, value, one) {
  list[key] = {
    value: value,
    one: one
  };
}

cache.get = function (key, disposable) {
  var data = list[key];
  if (data == null) return null;

  var value = data.value;
  if (data.one === true || disposable === true) {
    delete list[key];
  }

  return value;
}

cache.delete = function (key) {
  delete list[key];
}

//module.exports = cache;