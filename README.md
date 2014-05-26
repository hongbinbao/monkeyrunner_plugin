monkeyrunner_plugin
===================

android monkeyrunner plugin for automation test and support image comparison by sikuli API

#### Installation
    monkeyrunner
    verify.jar
    sikuli-script-32bits.jar on 32bits host
    sikuli-script-64bits.jar on 64bits host

#### demo
    monkeyrunner -plugin sikuli-script-32bits.jar -plugin verify.jar test.py
    
#### usage
    from com.android.monkeyrunner import MonkeyRunner as runner
    from com.android.monkeyrunner import MonkeyDevice as device
    from com.bhb.verify import Verifier as vf
    
    exist =  vf().find('phone_dial_icon.png', 'app_phone_screen')
    
    point =  vf().getLocation('phone_dial_icon.png', 'app_phone_screen')
    
    x_orientation_natural, y_orientation_natural = point
