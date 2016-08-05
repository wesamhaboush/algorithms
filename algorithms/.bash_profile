
alias cp='cp -iv'                           # Preferred 'cp' implementation
alias rm='rm -v'                           # Preferred 'cp' implementation
alias mv='mv -iv'                           # Preferred 'mv' implementation
alias mkdir='mkdir -pv'                     # Preferred 'mkdir' implementation
alias ll='ls -FGlAhp'                       # Preferred 'ls' implementation
alias l='ls'
alias less='less -FSRXc'

# Reset
Color_Off='\e[0m'       # Text Reset

# Regular Colors
Black='\e[0;30m'        # Black
Red='\e[0;31m'          # Red
Green='\e[0;32m'        # Green
Yellow='\e[0;33m'       # Yellow
Blue='\e[0;34m'         # Blue
Purple='\e[0;35m'       # Purple
Cyan='\e[0;36m'         # Cyan
White='\e[0;37m'        # White

# Bold
BBlack='\e[1;30m'       # Black
BRed='\e[1;31m'         # Red
BGreen='\e[1;32m'       # Green
BYellow='\e[1;33m'      # Yellow
BBlue='\e[1;34m'        # Blue
BPurple='\e[1;35m'      # Purple
BCyan='\e[1;36m'        # Cyan
BWhite='\e[1;37m'       # White

# Underline
UBlack='\e[4;30m'       # Black
URed='\e[4;31m'         # Red
UGreen='\e[4;32m'       # Green
UYellow='\e[4;33m'      # Yellow
UBlue='\e[4;34m'        # Blue
UPurple='\e[4;35m'      # Purple
UCyan='\e[4;36m'        # Cyan
UWhite='\e[4;37m'       # White

# Background
On_Black='\e[40m'       # Black
On_Red='\e[41m'         # Red
On_Green='\e[42m'       # Green
On_Yellow='\e[43m'      # Yellow
On_Blue='\e[44m'        # Blue
On_Purple='\e[45m'      # Purple
On_Cyan='\e[46m'        # Cyan
On_White='\e[47m'       # White

# High Intensity
IBlack='\e[0;90m'       # Black
IRed='\e[0;91m'         # Red
IGreen='\e[0;92m'       # Green
IYellow='\e[0;93m'      # Yellow
IBlue='\e[0;94m'        # Blue
IPurple='\e[0;95m'      # Purple
ICyan='\e[0;96m'        # Cyan
IWhite='\e[0;97m'       # White

# Bold High Intensity
BIBlack='\e[1;90m'      # Black
BIRed='\e[1;91m'        # Red
BIGreen='\e[1;92m'      # Green
BIYellow='\e[1;93m'     # Yellow
BIBlue='\e[1;94m'       # Blue
BIPurple='\e[1;95m'     # Purple
BICyan='\e[1;96m'       # Cyan
BIWhite='\e[1;97m'      # White

# High Intensity backgrounds
On_IBlack='\e[0;100m'   # Black
On_IRed='\e[0;101m'     # Red
On_IGreen='\e[0;102m'   # Green
On_IYellow='\e[0;103m'  # Yellow
On_IBlue='\e[0;104m'    # Blue
On_IPurple='\e[0;105m'  # Purple
On_ICyan='\e[0;106m'    # Cyan
On_IWhite='\e[0;107m'   # White

set_prompt () {
    LAST_COMMAND=$? # Must come first!
    Blue='\[\e[01;34m\]'
    White='\[\e[01;37m\]'
    Red='\[\e[01;31m\]'
    Green='\[\e[01;32m\]'
    Reset='\[\e[00m\]'
    FancyX='\342\234\227'
    Checkmark='\342\234\223'

    # Add a bright white exit status for the last command
    PS1=""
    DATE_NOW=$(/bin/date)
    PS1+="\n${Purple}${DATE_NOW}"
    PS1+="\n${Blue}\u @ \h"
    PS1+="${White} : ${Yellow}\w"
    PS1+="\n${White}${LAST_COMMAND} "
    # If it was successful, print a green check mark. Otherwise, print
    # a red X.
    if [[ ${LAST_COMMAND} == 0 ]]; then
        PS1+="${BGreen}${Checkmark} "
    else
        PS1+="${BIRed}${FancyX} "
    fi
    # Print the working directory and prompt marker in blue, and reset
    # the text color to the default.
    PS1+="${Black}\\\$ ${Reset}"
}
PROMPT_COMMAND='set_prompt'

# add my scripts to the path and export it
export PATH=$PATH:~/apps/scripts
export WEBGEN_PROPS=cloud-env

if [ -f ~/.git-completion.bash ]; then
 . ~/.git-completion.bash
fi

### Added by the Heroku Toolbelt
export PATH="/usr/local/heroku/bin:$PATH"
export PATH="/opt/oracle/instantclient_11_2:$PATH"
export ORACLE_HOME=/opt/oracle/instantclient_11_2
export DB2HOME=/Users/db2inst1/sqllib
export DYLD_LIBRARY_PATH="$DB2HOME/lib64:$ORACLE_HOME:$DYLD_LIBRARY_PATH"
export NVM_DIR=$(brew --prefix)/var/nvm
export IBM_DB_HOME=$DB2HOME
export PATH=/Users/m811695/.rvm/gems/ruby-2.0.0-p643/bin:$PATH
export PATH=/Users/m811695/.rvm/rubies/ruby-2.0.0-p643/bin:$PATH
export LC_ALL=en_US.UTF-8
export LANG=en_US.UTF-8
export LANGUAGE=en_US.UTF-8


source $(brew --prefix nvm)/nvm.sh

f(){ if [ "$PWD" != "$LPWD" ];then LPWD="$PWD"; tmux rename-window ${PWD//*\//}; fi }
export PROMPT_COMMAND='f;set_prompt;'

freeport(){
     lsof -i tcp:$1 | awk '{print $2}' | xargs kill -9
}

portuser(){
     lsof -i tcp:$1
}




[[ -s "$HOME/.rvm/scripts/rvm" ]] && source "$HOME/.rvm/scripts/rvm" # Load RVM into a shell session *as a function*


alias komodo='open -a "/Applications/Komodo\ Edit\ 9.app/Contents/MacOS/komodo"'

nvm use v4.4.3
