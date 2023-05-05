import SwiftUI
import shared

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


struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainScreenIosKt.MainViewController(openCameraClicked: { openCameraClicked in
            if openCameraClicked as! Bool {
                if let topViewController = UIApplication.shared.windows.first?.rootViewController?.topViewController {
                    topViewController.present(
                        UIHostingController(
                            rootView: MyPickerView(onButtonDismissClicked: {
                                topViewController.dismiss(animated: true)
                            })
                        ),
                        animated: true,
                        completion: nil
                    )
                }
            }
        }, bitmapImageResult: )
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        
    }
}

struct MyPickerView: View {
    @State private var image: Image? = Image("")
    @State private var shouldPresentImagePicker = false
    @State private var shouldPresentActionScheet = false
    @State private var shouldPresentCamera = false
    let onButtonDismissClicked: () -> Void
    
    var body: some View {
        VStack {
            Spacer()
            
            MyImagePickerView(
                sourceType: .photoLibrary,
                image: self.$image,
                isPresented: self.$shouldPresentImagePicker
            )
            
//            image!
//                .resizable()
//                .aspectRatio(contentMode: .fill)
//                .frame(width: 300, height: 300)
//                .clipShape(Circle())
//                .overlay(Circle().stroke(Color.white, lineWidth: 4))
//                .shadow(radius: 10)
//                .onTapGesture { self.shouldPresentActionScheet = true }
//                .sheet(isPresented: $shouldPresentImagePicker) {
//                    MyImagePickerView(
//                        sourceType: self.shouldPresentCamera ? .camera : .photoLibrary,
//                        image: self.$image,
//                        isPresented: self.$shouldPresentImagePicker
//                    )
//                }
//                .actionSheet(isPresented: $shouldPresentActionScheet) { () -> ActionSheet in
//                    ActionSheet(
//                        title: Text("Choose mode"),
//                        message: Text("Please choose your preferred mode to set your profile image"),
//                        buttons:
//                            [
////                                ActionSheet.Button.default(
////                                    Text("Camera"),
////                                    action: {
////                                        self.shouldPresentImagePicker = true
////                                        self.shouldPresentCamera = true
////
////                                    }
////                                ),
//                                ActionSheet.Button.default(
//                                    Text("Photo Library"),
//                                    action: {
//                                        self.shouldPresentImagePicker = true
//                                        self.shouldPresentCamera = false
//
//                                    }
//                                 ),
//                                ActionSheet.Button.cancel()
//                            ]
//                    )
//                }
//
            
            Spacer()
            
            Button("Dismiss", action: {
                onButtonDismissClicked()
            })
        }
    }
}

struct ContentView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}
