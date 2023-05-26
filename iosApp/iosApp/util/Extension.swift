//
//  Extension.swift
//  iosApp
//
//  Created by Quipper Indonesia on 26/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import UIKit

extension UIViewController {
    var topViewController: UIViewController? {
        if let presented = presentedViewController {
            return presented.topViewController
        }
        if let nav = self as? UINavigationController {
            return nav.visibleViewController?.topViewController
        }
        if let tab = self as? UITabBarController {
            return tab.selectedViewController?.topViewController
        }
        return self
    }
}
