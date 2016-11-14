import Rx from 'rxjs/Rx';
const { ajax } = Rx.Observable;
import Utils from './Utils';

const ajaxWrapper = (opts = {}) => {
  let defaults = {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    responseType: 'json'
  };
  let options = Utils.merge(defaults, opts);

  let makeRequest = function (options) {
    var requestOptions = {
      url: options.url,
      method: options.method,
      headers: options.headers,
      credentials: 'same-origin',
      responseType: options.responseType
    };

    if (options.method !== 'GET' && options.method !== 'HEAD') {
      Object.assign(requestOptions, {
        body: options.body
      });
    }

    return ajax(requestOptions);
  };

  return makeRequest(options);
};

export default ajaxWrapper;
