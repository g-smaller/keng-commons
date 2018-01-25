package com.keng.common;

import com.keng.common.util.StringUtil;

public class Words {

    public static final String EMPTY                                = "";
    public static final String META_INF                             = "META-INF/";

    public enum Extension {
        CLASS(".class"),

        ;

        public String ext;

        Extension(String ext) {
            this.ext = ext;
        }

        public String getExt() {
            return ext;
        }

        public boolean eq(String ext) {
            return StringUtil.isNotEmpty(ext) && this.getExt().equals(ext);
        }
    }

    public static class Propeties {
        public static final String JAVA_RUNTIME_NAME                = "java.runtime.name";
        public static final String JAVA_RUNTIME_VERSION             = "java.runtime.version";
        public static final String JAVA_VERSION                     = "java.version";

        public static final String SUN_BOOT_LIBRARY_PATH            = "sun.boot.library.path";
        public static final String JAVA_VM_VERSION                  = "java.vm.version";
        public static final String GOPHER_PROXY_SET                 = "gopherProxySet";
        public static final String JAVA_VM_VENDOR                   = "java.vm.vendor";
        public static final String JAVA_VENDOR_URL                  = "java.vendor.url";

        // like :
        public static final String PATH_SEPARATOR                   = "path.separator";
        // like \n
        public static final String LINE_SEPARATOR                   = "line.separator";
        // like /
        // windows \\
        public static final String FILE_SEPARATOR                   = "file.separator";

        public static final String JAVA_VM_NAME                     = "java.vm.name";
        public static final String FILE_ENCODING_PKG                = "file.encoding.pkg";

        public static final String SUN_JAVA_LAUNCHER                = "sun.java.launcher";
        public static final String SUN_OS_PATCH_LEVEL               = "sun.os.patch.level";
        public static final String JAVA_VM_SPECIFICATION_NAME       = "java.vm.specification.name";


        public static final String JAVA_AWT_GRAPHICSENV             = "java.awt.graphicsenv";
        public static final String ORG_JBOSS_LOGGING_PROVIDER       = "org.jboss.logging.provider";
        public static final String JAVA_ENDORSED_DIRS               = "java.endorsed.dirs";

        public static final String JAVA_IO_TMPDIR                   = "java.io.tmpdir";

        public static final String JAVA_VM_SPECIFICATION_VENDOR     = "java.vm.specification.vendor";

        // 所属地区 CN
        public static final String USER_COUNTRY                     = "user.country";
        // 当前项目目录
        public static final String USER_DIR                         = "user.dir";
        // china default Asia/Shanghai
        public static final String USER_TIMEZONE                    = "user.timezone";
        // 当前用户主目录
        public static final String USER_HOME                        = "user.home";
        // 当前用户名字
        public static final String USER_NAME                        = "user.name";
        // 当前用户使用的系统语言
        public static final String USER_LANGUAGE                    = "user.language";

        public static final String OS_ARCH                          = "os.arch";
        public static final String OS_NAME                          = "os.name";
        public static final String OS_VERSION                       = "os.version";
        // like UTF-8
        public static final String SUN_JNU_ENCODING                 = "sun.jnu.encoding";
        // default UTF-8
        public static final String FILE_ENCODING                    = "file.encoding";

        public static final String JAVA_LIBRARY_PATH                = "java.library.path";
        public static final String JAVA_SPECIFICATION_NAME          = "java.specification.name";
        public static final String JAVA_CLASS_VERSION               = "java.class.version";
        public static final String SUN_MANAGEMENT_COMPILER          = "sun.management.compiler";
        public static final String HTTP_NON_PROXY_HOSTS             = "http.nonProxyHosts";
        public static final String JAVA_AWT_PRINTERJOB              = "java.awt.printerjob";

        public static final String JAVA_SPECIFICATION_VERSION       = "java.specification.version";
        public static final String JAVA_VM_SPECIFICATION_VERSION    = "java.vm.specification.version";

        public static final String JAVA_CLASS_PATH                  = "java.class.path";
        public static final String SUN_JAVA_COMMAND                 = "sun.java.command";
        /**
         * @see {@link Environment#JAVA_HOME}
         */
        public static final String JAVA_HOME                        = "java.home";
        public static final String SUN_ARCH_DATA_MODEL              = "sun.arch.data.model";
        public static final String JAVA_specification_VENDOR        = "java.specification.vendor";
        public static final String AWT_TOOLKIT                      = "awt.toolkit";
        public static final String JAVA_VM_INFO                     = "java.vm.info";
        // 扩展目录
        public static final String JAVA_EXT_DIRS                    = "java.ext.dirs";
        public static final String SUN_BOOT_CLASS_PATH              = "sun.boot.class.path";
        public static final String JAVA_AWT_HEADLESS                = "java.awt.headless";
        public static final String JAVA_VENDOR                      = "java.vendor";
        public static final String JAVA_VENDOR_URL_BUG              = "java.vendor.url.bug";
        public static final String SUN_IO_UNICODE_ENCODING          = "sun.io.unicode.encoding";
        // like little
        public static final String SUN_CPU_ENDIAN                   = "sun.cpu.endian";
        public static final String SOCKS_NON_PROXY_HOSTS            = "socksNonProxyHosts";
        public static final String FTP_NON_PROXY_HOSTS              = "ftp.nonProxyHosts";
        public static final String SUN_CPU_ISALIST                  = "sun.cpu.isalist";
    }

    public static class Environment {
        public static final String PATH                             = "PATH";
        /**
         * @see {@link Propeties#JAVA_HOME}
         */
        public static final String JAVA_HOME                        = "JAVA_HOME";
        public static final String COMMAND_MODE                     = "COMMAND_MODE";
        public static final String VERSIONER_PYTHON_VERSION         = "VERSIONER_PYTHON_VERSION";
        /**
         * @see {@link Propeties#USER_NAME}
         */
        public static final String LOGNAME                          = "LOGNAME";
        public static final String PWD                              = "PWD";
        public static final String SHELL                            = "SHELL";
        public static final String PAGER                            = "PAGER";
        public static final String LSCOLORS                         = "LSCOLORS";
        public static final String OLDPWD                           = "OLDPWD";
        public static final String SECURITYSESSIONID                = "SECURITYSESSIONID";
        /**
         * @see {@link Propeties#USER_NAME}
         */
        public static final String USER                             = "USER";
        /**
         * @see {@link Propeties#JAVA_IO_TMPDIR}
         */
        public static final String TMPDIR                           = "TMPDIR";
        public static final String LESS                             = "LESS";
        /**
         * @see {@link Propeties#USER_HOME}
         */
        public static final String HOME                             = "HOME";
    }

}
