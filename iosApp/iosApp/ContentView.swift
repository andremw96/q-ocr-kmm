import SwiftUI
import shared


struct ContentView: View {
    @State private var image: UIImage? = UIImage()
    
    var body: some View {
        ComposeView(onImagePicked: { pickedImage in
            image = pickedImage
        }).ignoresSafeArea(.keyboard) // Compose has its own keyboard handler
    
        if let image = image {
            Image(uiImage: image)
                .resizable()
                .scaledToFit()
        } else {
            Text("please select image")
        }
        
//        VStack {
//            Spacer()
//
//
//
//            Spacer()
//        }
    }
}

struct ComposeView: UIViewControllerRepresentable {
    @State var image: UIImage?
    let onImagePicked: (UIImage?) -> Void
    
    func makeUIViewController(context: Context) -> UIViewController {
        return MainScreenIosKt.MainViewController(openCameraClicked: {
            if let topViewController = UIApplication.shared.windows.first?.rootViewController?.topViewController {
                let pickerViewController = UIHostingController(
                    rootView: MyPickerView(onButtonDismissClicked: { imageResult in
                        context.coordinator.imagePicked(imageResult)
                        topViewController.dismiss(animated: true)
                    })
                )
                topViewController.present(pickerViewController, animated: true)
            }
        })
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        
    }
    
    func makeCoordinator() -> Coordinator {
        Coordinator(self)
    }
    
    class Coordinator: NSObject {
        let parent: ComposeView
        
        init(_ parent: ComposeView) {
            self.parent = parent
        }
        
        func imagePicked(_ image: UIImage?) {
            parent.image = image
            parent.onImagePicked(image)
        }
    }
}

