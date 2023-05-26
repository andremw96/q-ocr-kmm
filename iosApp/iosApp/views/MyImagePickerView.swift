//
//  CameraView.swift
//  iosApp
//
//  Created by Quipper Indonesia on 05/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import UIKit
import SwiftUI

struct MyPickerView: View {
    let onButtonDismissClicked: ((UIImage?)) -> Void
    
    var body: some View {
        VStack {
            Spacer()
            
            MyImagePickerView(
                sourceType: .photoLibrary,
                shouldDismissImagePickerView: onButtonDismissClicked
            )
            
            Spacer()
            
            Button("Dismiss", action: {
                onButtonDismissClicked(nil)
            })
        }
    }
}

struct MyImagePickerView: UIViewControllerRepresentable {
    
    var sourceType: UIImagePickerController.SourceType = .photoLibrary
    let shouldDismissImagePickerView: ((UIImage?)) -> Void
    
    func makeCoordinator() -> ImagePickerViewCoordinator {
        return ImagePickerViewCoordinator(shouldDismissImagePickerView: shouldDismissImagePickerView)
    }
    
    func makeUIViewController(context: Context) -> UIImagePickerController {
        let pickerController = UIImagePickerController()
        pickerController.sourceType = sourceType
        pickerController.delegate = context.coordinator
        return pickerController
    }

    func updateUIViewController(_ uiViewController: UIImagePickerController, context: Context) {
        // Nothing to update here
        
    }

}

class ImagePickerViewCoordinator: NSObject, UINavigationControllerDelegate, UIImagePickerControllerDelegate {
    
    let shouldDismissImagePickerView: ((UIImage?)) -> Void
    
    init(shouldDismissImagePickerView: @escaping ((UIImage?)) -> Void) {
        self.shouldDismissImagePickerView = shouldDismissImagePickerView
    }
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        if let image = info[UIImagePickerController.InfoKey.originalImage] as? UIImage {
            self.shouldDismissImagePickerView(image)
        }
    }
    
    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        self.shouldDismissImagePickerView(nil)
    }
    
}
