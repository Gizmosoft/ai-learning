from datasets import load_dataset

# Stream the dataset (loads entries one by one, no slicing here)
dataset = load_dataset("allenai/dolma", split="train", streaming=True, trust_remote_code=True)

# Manually limit to 10 examples
with open("results.txt", "w", encoding="utf-8") as f:
    f.write("DOLMA dataset's first 10 entries:\n\n")
    for i, item in enumerate(dataset):
        if i >= 10:
            break
        print(f"{i+1}. {item['text'][:200]}...\n")  # printing only first 200 characters
        f.write(f"{i+1}. {item['text'][:200]}...\n\n")

