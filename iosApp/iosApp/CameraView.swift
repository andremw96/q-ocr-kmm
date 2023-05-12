//
//  CameraView.swift
//  iosApp
//
//  Created by Quipper Indonesia on 05/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import UIKit
import SwiftUI

struct MyImagePickerView: UIViewControllerRepresentable {
    
    var sourceType: UIImagePickerController.SourceType = .photoLibrary
    @Binding var image: UIImage?
    let shouldDismissImagePickerView: () -> Void
    
    func makeCoordinator() -> ImagePickerViewCoordinator {
        return ImagePickerViewCoordinator(image: $image, shouldDismissImagePickerView: shouldDismissImagePickerView)
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
    
    @Binding var image: UIImage?
    let shouldDismissImagePickerView: () -> Void
    
    init(image: Binding<UIImage?>, shouldDismissImagePickerView: @escaping () -> Void) {
        self._image = image
        self.shouldDismissImagePickerView = shouldDismissImagePickerView
    }
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        if let image = info[UIImagePickerController.InfoKey.originalImage] as? UIImage {
            self.image = image
        }
        self.shouldDismissImagePickerView()
    }
    
    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        self.shouldDismissImagePickerView()
    }
    
}
