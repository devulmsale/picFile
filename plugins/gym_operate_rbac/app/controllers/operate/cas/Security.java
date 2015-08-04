/**
 *  This file is part of LogiSima-play-cas.
 *
 *  LogiSima-play-cas is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  LogiSima-play-cas is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with LogiSima-play-cas.  If not, see <http://www.gnu.org/licenses/>.
 */
package controllers.operate.cas;

import controllers.OperateRbac;
import play.Play;
import play.cache.Cache;
import play.mvc.Scope;

/**
 * The Security class interface. This is the entry point where you can plug your own security manager, like how to check
 * rights, how to define your own user object and put in cache (not in session !!).
 *
 * @author bsimard
 *
 */
public class Security {

    private static String _loginNameForTest = null;

    /**
     * 设置当前线程为已经登录。只在DEV模式下有效。
     * 注意: 只应用于FunctionTest!
     * @param login
     */
    public static void setLoginUserForTest(String login) {
        if (Play.mode == Play.Mode.DEV) {
            _loginNameForTest = login;
            Cache.add(OperateRbac.SESSION_USER_ID_KEY + _loginNameForTest, Boolean.TRUE);
        }
    }

    /**
     * 在@After方法中要调用一下这个，以避免影响selenium.
     */
    public static void cleanLoginUserForTest() {
        Cache.delete(OperateRbac.SESSION_USER_ID_KEY + _loginNameForTest);
        _loginNameForTest = null;
    }

    public static String getLoginUserForTest() {
        if (Play.mode == Play.Mode.DEV) {
            return _loginNameForTest;
        }
        return null;
    }

    public static boolean isTestLogined() {
        if (Play.mode != Play.Mode.DEV) {
            return false;
        }
        return _loginNameForTest != null;
    }

    /**
     * Method to check user's profile.
     *
     * @param profile
     * @return
     */
    public static boolean check(String profile) {
        return true;
    }

    /**
     * Method that return the user object. By default, it's only check session and return the username.
     *
     * @return
     */
    public static Object connected() {
        return Scope.Session.current().get("username");
    }

    /**
     * Method that check if the user if connected.
     *
     * @return
     */
    public static boolean isConnected() {
        return Scope.Session.current().contains("username");
    }

}
