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
    
    natural_point =  vf().getLocation('phone_dial_icon.png', 'app_phone_screen')
    x_orientation_natural, y_orientation_natural = natural_point
    
    #search the touch-able image and return the center point location coordinate in natural screen orientation
    #monkeyrunner will always take the top left corner coordinates of app's real orientation as it's (0.0) point. 
    #similarity=0.8, rotation=90 (90,180,270 means real app screen orientation)
    #0, natural display, app display orientation
    #90, landscape display, rotating device 90 degrees counterclockwise
    #180, natural invert display, rotating device 180 degrees counterclockwise
    #270, landscape invert display, rotating device 180 degrees counterclockwise
    #similarity= 0.8, rotating device 90/180/270 degrees counterclockwise
    rotation_90_point = vf().getLocation('phone_dial_icon.png', 'app_phone_screen', 0.8, 90)
    
    x_rotation_90_counterclockwise, yx_rotation_90_counterclockwise = natural_point
