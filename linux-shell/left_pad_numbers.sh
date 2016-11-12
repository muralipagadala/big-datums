# left pad with zeros
printf "%05d\n" 99

# left pad with zeros and other text
printf "my-file-name-%05d.txt\n" 99

# left pad with zeros with awk
echo 99 | awk '{printf "%05d\n", $0}'

# left pad with zeros bash loop
for i in {00001..00009}; do 
  echo $i
done

