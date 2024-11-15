'use strict';

/**@class */
class Main {
    /**@private */ _webDebug = true;

    constructor() {
        this._init();
    }

    /**@private */
    _init() {
        if (this._webDebug) console.info('JavaScript подключён.');
    }
}

let main = new Main();