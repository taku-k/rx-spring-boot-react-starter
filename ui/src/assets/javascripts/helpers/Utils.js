export default class Utils {
  static merge = (opts, override) => {
    var ret = {};
    for (let attr in opts) ret[attr] = opts[attr];
    for (let attr in override) ret[attr] = override[attr];
    return ret;
  }
}
